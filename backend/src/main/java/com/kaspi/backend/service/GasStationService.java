package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.dto.FindGasStationReqDto;
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

    private final GasStationDao gasStationDao;

    public List<FindGasStationReqDto> getGasStationByContainingName(String reqGasStationName) {
        List<FindGasStationReqDto> matchingGasStations = new ArrayList<>();
        Iterable<GasStation> gasStations = gasStationDao.findAll();
        log.info("주유소 이름 검색 요청 name:{}", reqGasStationName);
        for (GasStation gasStation : gasStations) {
            insertMatchingGasStation(reqGasStationName, gasStation, matchingGasStations);
        }
        log.info("matching된 주유소 name:{}", matchingGasStations.stream().map(FindGasStationReqDto::getName).collect(Collectors.toList()));
        return matchingGasStations;
    }

    private void insertMatchingGasStation(String reqGasStationName, GasStation gasStation, List<FindGasStationReqDto> matchingGasStations) {
        if (gasStation.getName().contains(reqGasStationName)) {
            matchingGasStations.add(FindGasStationReqDto.builder()
                    .stationNo(gasStation.getStationNo())
                    .brand(gasStation.getBrand())
                    .name(gasStation.getName())
                    .area(gasStation.getArea())
                    .address(gasStation.getAddress())
                    .build());
        }
    }
}
