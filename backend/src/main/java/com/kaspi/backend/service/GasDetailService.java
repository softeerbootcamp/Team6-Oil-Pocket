package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.enums.GasType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GasDetailService {
    private static final String SPACE = " ";
    private static final String WILDCARD = "%";
    private final GasStationDao gasStationDao;
    private final GasDetailDao gasDetailDao;

    private List<GasDetailDto> convertToGasDetailDtoList(List<GasDetail> gasDetailList) {
        List<GasDetailDto> gasDetailDtoList;
        // 오일이 리스트에 있는 경우, LPG empty 상세정보 추가
        if (gasDetailList.size() > 1) {
            gasDetailDtoList = GasDetailDto.newDtoList(gasDetailList);
            gasDetailDtoList.add(GasDetailDto.makeEmptyDetailDto(GasType.LPG));
            return gasDetailDtoList;
        }
        // LPG 만 리스트에 있는 경우, 오일 종류들의 empty 상세정보를 추가
        gasDetailDtoList = GasDetailDto.makeEmptyOilDetailDtoList();
        gasDetailDtoList.add(gasDetailDtoList.get(0));
        return gasDetailDtoList;
    }

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
