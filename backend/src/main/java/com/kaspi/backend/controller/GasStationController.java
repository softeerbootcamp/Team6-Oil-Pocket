package com.kaspi.backend.controller;

import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStationDto;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/gas-station")
@RequiredArgsConstructor
public class GasStationController {
    private final GasStationService gasStationService;

    @GetMapping("/{name}/{roadName}/{buildNum}/{brand}")
    public ResponseEntity<CommonResponseDto> getGasStationInfoNow(@PathVariable("name") String name,
                                                                  @PathVariable("roadName") String roadName,
                                                                  @PathVariable("buildNum") String buildNum,
                                                                  @PathVariable("brand") String brand) {
        GasStationDto gasStationDto = gasStationService.findGasStationDto(name, roadName, buildNum, brand);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_TO_FIND_GAS_DEATIL, gasStationDto));
    }
    @GetMapping("/{name}/{roadName}/{buildNum}/{brand}/month")
    public ResponseEntity<CommonResponseDto> getGasStationInfoMonth(@PathVariable("name") String name,
                                                                  @PathVariable("roadName") String roadName,
                                                                  @PathVariable("buildNum") String buildNum,
                                                                  @PathVariable("brand") String brand) {
        GasStationDto gasStationDto = gasStationService.findOntMonthGasStationDto(name, roadName, buildNum, brand);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_TO_FIND_GAS_DEATIL, gasStationDto));
    }
}
