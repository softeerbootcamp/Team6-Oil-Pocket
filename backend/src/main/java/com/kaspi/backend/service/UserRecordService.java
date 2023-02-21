package com.kaspi.backend.service;

import com.kaspi.backend.dao.FoodImageDao;
import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.dao.UserGasRecordDao;
import com.kaspi.backend.domain.*;
import com.kaspi.backend.dto.UserEcoRecordResDto;
import com.kaspi.backend.dto.UserGasRecordMonthResDto;
import com.kaspi.backend.dto.UserGasRecordReqDto;
import com.kaspi.backend.dto.UserGasRecordResDto;
import com.kaspi.backend.util.exception.SqlNotFoundException;
import com.kaspi.backend.util.response.code.ErrorCode;

import java.time.LocalDate;
import java.util.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRecordService {
    private final GasDetailDao gasDetailDao;
    private final GasStationDao gasStationDao;
    private final HttpSessionService httpSessionService;
    private final UserGasRecordDao userGasRecordDao;

    private final FoodImageDao foodImageDao;

    /**
     * 사용자로 부터 받은 주유금액, 유종과 주유소 정보로 얼마만큼 넣었는지 계산하는 로직
     */
    public Long calTodayUserGasAmount(UserGasRecordReqDto userGasRecordReqDto, GasStation gasStation) {
        Optional<Long> todayGasPrice = gasDetailDao.findTodayGasPrice(gasStation.getStationNo(),
                userGasRecordReqDto.getGasType().name(),
                GasDetail.getNowDateToStr());//오늘 날짜로 계산
        if (todayGasPrice.isEmpty() || todayGasPrice.get() == 0) {
            log.error("DB에 오늘날짜에 해당되는 주유 가격 정보가 존재하지 않음 가스타입:{}, 주유소PK:{}", userGasRecordReqDto.getGasType().name(), gasStation.getStationNo());
            throw new SqlNotFoundException(ErrorCode.SQL_NOT_FOUND);
        }
        Long userGasAmount = (long) Math.round(userGasRecordReqDto.getRefuelingPrice() / todayGasPrice.get());
        log.info("사용자가 주유한 가스타입:{}, 주유량:{}", userGasRecordReqDto.getGasType().name(), userGasAmount);
        return userGasAmount;
    }

    public Long calUserSavingAmount(Long userRefuelingPrice, Long userGasAmount, Long nationalAvgOilPrice) {
        return userRefuelingPrice - nationalAvgOilPrice * userGasAmount;
    }


    @Transactional
    public UserGasRecord saveUserGasRecord(UserGasRecordReqDto userGasRecordReqDto, GasStation gasStation,
                                           Long userGasAmount, Long usersSavingPrice) {
        User user = httpSessionService.getUserFromSession();
        UserGasRecord userGasRecord = UserGasRecord.builder().userNo(user.getUserNo())
                .gasStationNo(gasStation.getStationNo())
                .chargeDate(LocalDate.now())
                .refuelingPrice(userGasRecordReqDto.getRefuelingPrice())
                .recordGasAmount(userGasAmount) //주유소 주유량
                .savingPrice(usersSavingPrice) // 사용자 전국 유가 평균값  가져오기
                .recordGasType(userGasRecordReqDto.getGasType()) // 여기서 변환할때 영어로 에러뜰려나?
                .build();
        log.info("유저 주유기록 저장 유저No:{}", user.getUserNo());
        return userGasRecordDao.save(userGasRecord);
    }

    /**
     * 유저가 저장한 주유기록을 가져오는 로직
     */
    public List<UserGasRecordResDto> getUserRecords() {
        User user = httpSessionService.getUserFromSession();
        List<UserGasRecord> matchingUserGasRecords = userGasRecordDao.findGasRecordListByUserId(user.getUserNo());
        List<UserGasRecordResDto> userGasRecordResDtos = toUserGasRecordResDtos(matchingUserGasRecords);
        return userGasRecordResDtos;
    }

    private List<UserGasRecordResDto> toUserGasRecordResDtos(List<UserGasRecord> matchingUserGasRecords) {
        List<UserGasRecordResDto> userRecordsRes = new ArrayList<>();

        for (UserGasRecord userGasRecord : matchingUserGasRecords) {
            GasStation findGasStation = getGasStation(userGasRecord);
            //유저 주유 기록과 주유할때 입력한 주유소를 통해 주유기록 열람 dto를 생성
            userRecordsRes.add(UserGasRecordResDto.toUserGasRecordResDto(userGasRecord, findGasStation));
        }
        return userRecordsRes;
    }

    private GasStation getGasStation(UserGasRecord userGasRecord) {
        Optional<GasStation> gasStation = gasStationDao.findById(userGasRecord.getGasStationNo());
        if (gasStation.isEmpty()) {
            log.error("GasStation을 가져오는 쿼리에서 해당 GasStation이 없습니다. gasStationNo:{}", userGasRecord.getGasStationNo());
            throw new SqlNotFoundException(ErrorCode.SQL_NOT_FOUND);
        }
        return gasStation.get();
    }

    public UserEcoRecordResDto calMonthUserEcoPrice() {
        User user = httpSessionService.getUserFromSession();
        LocalDate date = LocalDate.now();

        // 이번 달 유저가 속한 (나이대, 성별) 그룹원을 반환합니다
        List<EcoRecord> rankSavingPrices = userGasRecordDao.findSavingPriceByGenderAndAge(user.getGender(), user.getAge(), date)
                .orElseThrow(() -> new SqlNotFoundException(ErrorCode.SQL_NOT_FOUND));
        // 리스트에서 유저의 레코드를 뽑아냅니다
        int userIndex = rankSavingPrices.indexOf(EcoRecord.builder().userNo(user.getUserNo()).build());
        EcoRecord userEcoRecord = rankSavingPrices.get(userIndex);
        long ecoPrice = userEcoRecord.getSavingPrice();
        long refuelingPrice = userEcoRecord.getRefuelingPrice();

        long average = getAverage(rankSavingPrices);
        double perRank = (double) Math.round((double) userEcoRecord.getSavingRank() / (double) rankSavingPrices.size() * 10000) / 100;

        FoodImage foodImage = foodImageDao.findFoodImageByEcoPrice(BigDecimal.valueOf(Math.abs(ecoPrice)))
                .orElseThrow(() -> new SqlNotFoundException(ErrorCode.NOT_FOUND_FOOD_IMAGE));

        return UserEcoRecordResDto.builder().userId(user.getId())
                .gender(user.getGender()).age(user.getAge().getAgeBound()).refuelingPrice(refuelingPrice).myEcoPrice(ecoPrice)
                .averageEcoPrice(average).imageUrl(foodImage.getImageUrl()).rankPercentage(perRank)
                .build();
    }

    private long getAverage(List<EcoRecord> rankSavingPrices) {
        double totalSavingPrice = rankSavingPrices.stream().mapToDouble(EcoRecord::getSavingPrice).sum();
        return (long) (totalSavingPrice / rankSavingPrices.size());
    }


    /**
     * 사용자의 월별 주유금액/ 전국 유가 평균값의 주유금액 비교 로직 값 들고오기
     */
    public List<UserGasRecordMonthResDto> getUsersRecordPerMonth() {
        User user = httpSessionService.getUserFromSession();
        Optional<List<UserGasRecordMonthResDto>> sumRecordGroupByMonth = userGasRecordDao.findSumRecordGroupByMonth(user.getUserNo());
        checkUserRecordPerMonth(user, sumRecordGroupByMonth);
        return sumRecordGroupByMonth.get();
    }

    private void checkUserRecordPerMonth(User user, Optional<List<UserGasRecordMonthResDto>> sumRecordGroupByMonth) {
        if (sumRecordGroupByMonth.isEmpty()) {
            log.debug("사용자의 월별 주유기록 부분에서 sql쿼리문에서 문제가 생겼습니다. 사용자 PK:{}", user.getUserNo());
            throw new SqlNotFoundException(ErrorCode.SQL_NOT_FOUND);
        }
    }

}
