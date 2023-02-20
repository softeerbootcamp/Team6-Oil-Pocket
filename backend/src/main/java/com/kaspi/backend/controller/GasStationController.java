package com.kaspi.backend.controller;

import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.dto.FindGasStationResDto;
import com.kaspi.backend.service.GasStationService;
import com.kaspi.backend.service.HttpSessionService;
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

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GasStationController {
    private final GasStationService gasStationService;

    private final HttpSessionService httpSessionService;


    @GetMapping("/v1/gas-station/{name}/{roadName}/{buildNum}/{brand}")
    public ResponseEntity<CommonResponseDto> getGasStationInfoNow(@PathVariable("name") String name,
                                                                  @PathVariable("roadName") String roadName,
                                                                  @PathVariable("buildNum") String buildNum,
                                                                  @PathVariable("brand") String brand) {
        GasStationDto gasStationDto = gasStationService.findGasStationDto(name, roadName, buildNum, brand);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_TO_FIND_GAS_DETAIL, gasStationDto));
    }

    @GetMapping("/v2/gas-station")
    public ResponseEntity<CommonResponseDto> findGasStationByName(@RequestParam("name") String name) {
        List<FindGasStationResDto> matchingGasStations = gasStationService.getGasStationByContainingName(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.CHECK_MATCH_GAS_STATION, matchingGasStations));
    }

    @GetMapping("/v2/gas-station/recent")
    public ResponseEntity<CommonResponseDto> findGasStationRecent() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.FIND_RECENT_GAS_STATION, httpSessionService.getRecentGsListFromSession()));
    }

    @GetMapping("/v1/gas-station/{name}/{roadName}/{buildNum}/{brand}/month")
    public ResponseEntity<CommonResponseDto> getGasStationInfoMonth(@PathVariable("name") String name,
                                                                    @PathVariable("roadName") String roadName,
                                                                    @PathVariable("buildNum") String buildNum,
                                                                    @PathVariable("brand") String brand,
                                                                    HttpServletRequest request) {
        GasStationDto gasStationDto = gasStationService.findOntMonthGasStationDto(name, roadName, buildNum, brand);
        //로그인 되었을때 최근 본 주유소 세션 저장
        httpSessionService.addRecentStationView(gasStationDto,request.getSession(false));
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_TO_FIND_GAS_DETAIL, gasStationDto));
    }


}
