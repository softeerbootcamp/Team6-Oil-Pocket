package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.util.exception.SqlNotFoundException;
import com.kaspi.backend.util.response.code.ErrorCode;
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

    // 도로명, 건물번호, 브랜드로 주유소 가격 상세정보 리스트를 찾는 메소드 입니다
    public List<GasDetailDto> findGasDetailList(String roadNum, String buildNum, String brand) {
        GasStation gasStation = findGasStation(roadNum, buildNum, brand);
        Long gasStationNo = gasStation.getStationNo();
        Optional<List<GasDetail>> optionalGasDetailList = gasDetailDao.findByStationNoAndDate(gasStationNo, LocalDate.now());
        if (optionalGasDetailList.isEmpty()) {
            throw new SqlNotFoundException(this.getClass().getSimpleName(), ErrorCode.NOT_FOUND_GAS_DETAIL);
        }
        return convertToGasDetailDtoList(optionalGasDetailList.get());
    }

    //GasDetail 리스트를 GasDetailDto 리스트로 전환하는 메소드 입니다
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
