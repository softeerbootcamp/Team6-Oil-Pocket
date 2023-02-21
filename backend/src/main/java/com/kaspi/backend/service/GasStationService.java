package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.dto.FindGasStationResDto;
import com.kaspi.backend.enums.GasBrand;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.util.exception.SqlNotFoundException;
import com.kaspi.backend.util.response.code.ErrorCode;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GasStationService {
    private static final String SPACE = " ";
    private static final String WILDCARD = "%";
    private final GasStationDao gasStationDao;
    private final GasDetailService gasDetailService;

    public List<FindGasStationResDto> getGasStationByContainingName(String reqGasStationName, GasType requestGasType) {
        log.info("주유소 이름 검색 요청 name:{}, gasType:{}", reqGasStationName,requestGasType);
        List<FindGasStationResDto> gasStationForGasSearch = gasStationDao.findGastationForGasSearch(getLikeGasStationName(reqGasStationName), requestGasType.name());
        updateBrandToUri(gasStationForGasSearch);
        return gasStationForGasSearch;
    }

    private  void updateBrandToUri(List<FindGasStationResDto> gasStationForGasSearch) {
        for (FindGasStationResDto stationForGasSearch : gasStationForGasSearch) {
            stationForGasSearch.updateBrandToImage();
        }
    }


    public GasStation getGasStationByNo(Long gasStationNo) {
        Optional<GasStation> findGasStation = gasStationDao.findById(gasStationNo);
        if (findGasStation.isEmpty()) {
            throw new NoSuchElementException(ErrorCode.SQL_NOT_FOUND.getMessage());
        }
        return findGasStation.get();
    }


    private String getLikeGasStationName(String reqGasStationName) {
        return WILDCARD + reqGasStationName + WILDCARD;
    }

    public GasStationDto findGasStationDto(String name, String roadNum, String buildNum, String brand) {
        GasStation gasStation = findGasStation(roadNum, buildNum, brand);
        List<GasDetailDto> gasDetailDtoList = gasDetailService.findGasDetailList(gasStation);
        return GasStationDto.newInstance(name, gasStation, gasDetailDtoList);
    }

    // 도로명, 건물번호, 브랜드로 주유소를 찾는 메소드 입니다
    public GasStation findGasStation(String roadNum, String buildNum, String brand) {
        String address = roadNum + SPACE + buildNum;
        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand(address, brand);
        if (optionalGasStation.isEmpty()) {
            optionalGasStation = gasStationDao.findByLikeAddressAndBrand(getLikeAddress(roadNum, buildNum), brand + WILDCARD);
            if (optionalGasStation.isEmpty()) {
                throw new SqlNotFoundException(SqlNotFoundException.class.getSimpleName(), ErrorCode.NOT_FOUND_GAS_STATION);
            }
        }
        return optionalGasStation.get();
    }

    // 도로명 주소와 건물번호로 Like를 위한 와일드카드(%)를 설정해주는 메소드 입니다
    private String getLikeAddress(String roadNum, String buildNum) {
        return WILDCARD + roadNum + WILDCARD + buildNum + WILDCARD;
    }

    /**
     * 해당 주유소의 상세 가격 정보를 GasStationDto로 반환하는 메소드입니다.
     */
    public GasStationDto findOntMonthGasStationDto(String name, String roadNum, String buildNum, String brand) {
        GasStation gasStation = findGasStation(roadNum, buildNum, brand);
        List<GasDetailDto> gasDetailDtoList = gasDetailService.findOneMonthGasDetailList(gasStation);
        return GasStationDto.newInstance(name, gasStation, gasDetailDtoList);
    }
}
