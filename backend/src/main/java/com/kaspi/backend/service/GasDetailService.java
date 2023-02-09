package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasStation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GasDetailService {
    private static final String SPACE = " ";
    private static final String WILDCARD = "%";
    private final GasStationDao gasStationDao;

    public GasStation findGasStation(String roadNum, String buildNum, String brand) {
        String address = roadNum + SPACE + buildNum;
        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand(address, brand);
        if (optionalGasStation.isEmpty()) {
            optionalGasStation = gasStationDao.findByLikeAddressAndBrand(getLikeAddress(roadNum, buildNum), brand);
            if (optionalGasStation.isEmpty()) {
                throw new RuntimeException("존재하지 않는 주유소 입니다."); //TODO Exception 생성작업 해야함
            }
        }
        return optionalGasStation.get();
    }
    private String getLikeAddress(String roadNum, String buildNum) {
        return WILDCARD + roadNum + WILDCARD + buildNum;
    }
}
