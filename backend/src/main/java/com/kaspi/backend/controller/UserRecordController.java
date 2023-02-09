package com.kaspi.backend.controller;

import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.dao.RedisGasStationDao;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.dto.FindGasStationReqDto;
import com.kaspi.backend.service.GasStationService;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class UserRecordController {
    private final GasStationService gasStationService;

    @GetMapping("/gas-station")
    public ResponseEntity<CommonResponseDto> findGasStationByName(@RequestParam("name") String name) {
        List<FindGasStationReqDto> matchingGasStations = gasStationService.getGasStationByContainingName(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.CHECK_MATCH_GAS_STATION,matchingGasStations));
    }

}
