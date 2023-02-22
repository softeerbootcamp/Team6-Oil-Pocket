package com.kaspi.backend.service;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.domain.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import com.kaspi.backend.dto.FindGasStationResDto;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.util.exception.AuthenticationException;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class HttpSessionService {
    public static final String SESSION_KEY = "userNo";
    public static final String SESSION_RECENT_VIEW_STATION_KEY = "recentViewStations";

    private final HttpSession httpSession;
    private final UserDao userDao;
    private final AuthService authService;


    public void makeHttpSession(Long userNo) {
        httpSession.setAttribute(SESSION_KEY, userNo);
        log.info("세션 생성 - 요청 userNO:{}", userNo);
    }

    public User getUserFromSession() {
        Long userNo = (Long) httpSession.getAttribute(SESSION_KEY);
        Optional<User> findUser = userDao.findById(userNo);
        authService.checkNotValidUser(findUser);
        return findUser.get();
    }

    private void checkSessionValid(HttpSession session) {
        if (session == null) {
            log.error("세션이 만료되거나 없습니다.");
            throw new AuthenticationException(ErrorCode.AUTH_ERROR);
        }
    }

    public void deleteSession() {
        httpSession.invalidate();
    }


    /**
     * 사용자가 주유소 상세페이지를 들어갔을 경우 해당 주유소를 세션에 저장
     * 추후 주유기록 입력시에 최근 본 주유소를 리스트로 보여주기 위함
     * 로그인 되어 있지 않으면 실행되지 않는 함수
     */
    public void addRecentStationView(GasStationDto gasStationDto, HttpSession session) {
        try {
            checkSessionValid(session);
        } catch (AuthenticationException e) {
            return; //로그인이 되어 있지 않으면 패스
        }
        httpSession.setAttribute(SESSION_RECENT_VIEW_STATION_KEY, updateRecentGasStation(gasStationDto));
    }

    private List<FindGasStationResDto> updateRecentGasStation(GasStationDto gasStationDto) {
        List<FindGasStationResDto> recentGasStations = getRecentGsListFromSession();

        FindGasStationResDto findGasStationResDto = FindGasStationResDto.toFindDto(gasStationDto);

        addAvailGasType(gasStationDto, findGasStationResDto); //오늘날짜 기준 이용 가능한 주유소 타입만 insert

        if (!recentGasStations.contains(findGasStationResDto)) {
            recentGasStations.add(findGasStationResDto); //이전에 조회하지않았다면 session에 저장
            log.info("사용자가 주유소를 조회했습니다. 세션에 저장됩니다. 최근 본 주유소:{}", gasStationDto.getName());
        }
        return recentGasStations;
    }

    private void addAvailGasType(GasStationDto gasStationDto, FindGasStationResDto findGasStationResDto) {
        List<GasDetailDto> details = gasStationDto.getDetails();
        for (GasDetailDto detail : details) {
            if (detail.getDate().equals(LocalDate.now())) {
                findGasStationResDto.getGasTypes().add(detail.getGasType());
            }
        }
    }

    public List<FindGasStationResDto> getRecentGsListFromSession() {
        List<FindGasStationResDto> recentGasStation = (List<FindGasStationResDto>) httpSession.getAttribute(SESSION_RECENT_VIEW_STATION_KEY);
        recentGasStation = checkNewGsList(recentGasStation);
        return recentGasStation;
    }

    private List<FindGasStationResDto> checkNewGsList(List<FindGasStationResDto> recentGasStation) {
        if (recentGasStation == null || recentGasStation.size() == 0) {
            recentGasStation = new ArrayList<>(); //처음 조회한 경우
        }
        return recentGasStation;
    }

    /**
     * 요청 GasType과 일치하는 GasStation 찾기 로직
     */
    public List<FindGasStationResDto> getRecentGsListFromSession(GasType gasType) {
        List<FindGasStationResDto> recentGsListFromSession = getRecentGsListFromSession();
        List<FindGasStationResDto> matchingGasTypeForRecent = matchingGasType(gasType, recentGsListFromSession);
        return matchingGasTypeForRecent;
    }

    private List<FindGasStationResDto> matchingGasType(GasType gasType, List<FindGasStationResDto> recentGasStation) {
        List<FindGasStationResDto> matchingGasTypeForRecent = new ArrayList<>();
        for (FindGasStationResDto findGasStationResDto : recentGasStation) {
            if (findGasStationResDto.getGasTypes().contains(gasType)) {
                matchingGasTypeForRecent.add(findGasStationResDto);
            }
        }
        return matchingGasTypeForRecent;
    }


}
