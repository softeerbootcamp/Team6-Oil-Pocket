package com.kaspi.backend.controller;

import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.dto.FindGasStationResDto;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.service.GasStationService;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GasStationController {
    private final GasStationService gasStationService;


    @GetMapping("/v1/gas-station/{name}/{roadName}/{buildNum}/{brand}")
    public ResponseEntity<CommonResponseDto> getGasStationInfoNow(@PathVariable("name") String name,
                                                                  @PathVariable("roadName") String roadName,
                                                                  @PathVariable("buildNum") String buildNum,
                                                                  @PathVariable("brand") String brand) {
        GasStationDto gasStationDto = gasStationService.findGasStationDto(name, roadName, buildNum, brand);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_TO_FIND_GAS_DEATIL, gasStationDto));
    }

    @GetMapping("/v2/gas-station")
    public ResponseEntity<CommonResponseDto> findGasStationByName(@RequestParam("name") String name) {
        List<FindGasStationResDto> matchingGasStations = gasStationService.getGasStationByContainingName(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.CHECK_MATCH_GAS_STATION, matchingGasStations));
    }
}
