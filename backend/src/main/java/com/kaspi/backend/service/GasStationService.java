package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.util.exception.SqlNotFoundException;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GasStationService {
    private static final String SPACE = " ";
    private static final String WILDCARD = "%";
    private final GasStationDao gasStationDao;
    private final GasDetailService gasDetailService;

    public GasStationDto findGasStationDto(String name, String roadNum, String buildNum, String brand) {
        GasStation gasStation = findGasStation(roadNum, buildNum, brand);
        List<GasDetailDto> gasDetailDtoList = gasDetailService.findGasDetailList(gasStation, roadNum, buildNum, brand);
        return GasStationDto.newInstance(name, gasStation, gasDetailDtoList);
    }

    // 도로명, 건물번호, 브랜드로 주유소를 찾는 메소드 입니다
    public GasStation findGasStation(String roadNum, String buildNum, String brand) {
        String address = roadNum + SPACE + buildNum;
        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand(address, brand);
        if (optionalGasStation.isEmpty()) {
            optionalGasStation = gasStationDao.findByLikeAddressAndBrand(getLikeAddress(roadNum, buildNum), brand);
            if (optionalGasStation.isEmpty()) {
                throw new SqlNotFoundException(this.getClass().getSimpleName(), ErrorCode.NOT_FOUND_GAS_STATION);
            }
        }
        return optionalGasStation.get();
    }

    // 도로명 주소와 건물번호로 Like를 위한 와일드카드(%)를 설정해주는 메소드 입니다
    private String getLikeAddress(String roadNum, String buildNum) {
        return WILDCARD + roadNum + WILDCARD + buildNum;
    }
}
