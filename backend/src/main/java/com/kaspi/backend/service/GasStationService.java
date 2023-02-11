package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.dto.FindGasStationResDto;
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
    private static final String WILDCARD = "%";
    private final GasStationDao gasStationDao;

    public List<FindGasStationResDto> getGasStationByContainingName(String reqGasStationName) {
        List<FindGasStationResDto> matchingGasStations = new ArrayList<>();
        String name = "%" + reqGasStationName + "%";
        Iterable<GasStation> gasStationMatchingName = gasStationDao.findAllLikeName(getLikeGasStationName(reqGasStationName));
        log.info("주유소 이름 검색 요청 name:{}", reqGasStationName);
        for (GasStation gasStation : gasStationMatchingName) {
            insertMatchingGasStation(gasStation, matchingGasStations);
        }
        log.info("matching된 주유소 name:{}", matchingGasStations.stream().map(FindGasStationResDto::getName).collect(Collectors.toList()));
        return matchingGasStations;
    }

    private void insertMatchingGasStation(GasStation gasStation, List<FindGasStationResDto> matchingGasStations) {
        matchingGasStations.add(FindGasStationResDto.builder()
                .stationNo(gasStation.getStationNo())
                .brand(gasStation.getBrand())
                .name(gasStation.getName())
                .area(gasStation.getArea())
                .address(gasStation.getAddress())
                .build());

    }

    public GasStation getGasStationByNo(Long gasStationNo) {
        Optional<GasStation> findGasStation = gasStationDao.findById(gasStationNo);
        if (findGasStation.isEmpty()) {
            throw new NoSuchElementException(ErrorCode.GAS_STATION_NOT_FOUND.getMessage());
        }
        return findGasStation.get();
    }

    private String getLikeAddress(String roadNum, String buildNum) {
        return WILDCARD + roadNum + WILDCARD + buildNum;
    }

    private String getLikeGasStationName(String reqGasStationName) {
        return WILDCARD + reqGasStationName + WILDCARD;
    }
}
