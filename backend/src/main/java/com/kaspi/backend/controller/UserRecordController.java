package com.kaspi.backend.controller;

import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.dto.UserGasRecordReqDto;
import com.kaspi.backend.service.GasStationService;
import com.kaspi.backend.service.HttpSessionService;
import com.kaspi.backend.service.OpinetService;
import com.kaspi.backend.service.UserRecordService;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
@Slf4j
public class UserRecordController {
    @Autowired
    GasStationDao gasStationDao;
    @Autowired
    HttpSessionService httpSessionService;
    @Autowired
    GasDetailDao gasDetailDao;
    private final GasStationService gasStationService;
    private final UserRecordService userRecordService;
    private final OpinetService opinetService;


    @PostMapping("/user/gas-record")
    public ResponseEntity<CommonResponseDto> postUserGasRecord(@RequestBody UserGasRecordReqDto userGasRecordReqDto) {

        //주유소 객체 얻기
        GasStation gasStation = gasStationService.getGasStationByNo(userGasRecordReqDto.getGasStationNo());

        //유저의 주유량
        Long userGasAmount = userRecordService.calTodayUserGasAmount(userGasRecordReqDto, gasStation);

        //전국 유가 평균 받기
        Long nationalAvgOilPrice = opinetService.nationalAvgOilPrice(userGasRecordReqDto.getGasType());

        //사용자가 얼마만큼 손해/이익을 봤는지
        Long usersSavingPrice = userRecordService.calUserSavingAmount(
                userGasRecordReqDto.getRefuelingPrice(),
                userGasAmount,
                nationalAvgOilPrice
        );

        //유저 최종 저장
        userRecordService.saveUserGasRecord(userGasRecordReqDto,
                gasStation,
                userGasAmount,
                usersSavingPrice);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponseDto.toResponse(DefaultCode.SAVE_USER_GAS_RECORD));
    }



}
