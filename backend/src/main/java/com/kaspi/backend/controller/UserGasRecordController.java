package com.kaspi.backend.controller;


import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.dao.UserGasRecordDao;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.dto.UserGasRecordReqDto;
import com.kaspi.backend.dto.UserGasRecordResDto;
import com.kaspi.backend.service.GasStationService;
import com.kaspi.backend.service.OpinetService;
import com.kaspi.backend.service.UserRecordService;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
@Slf4j
public class UserGasRecordController {
    private final GasStationService gasStationService;
    private final UserRecordService userRecordService;
    private final OpinetService opinetService;


    @PostMapping("/user/gas-record")
    public ResponseEntity<CommonResponseDto> postUserGasRecord(@RequestBody UserGasRecordReqDto userGasRecordReqDto) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body(CommonResponseDto.toResponse(ErrorCode.PARAMETER_ERROR));
//        }
        //주유소 객체 얻기
        GasStation gasStation = gasStationService.getGasStationByNo(userGasRecordReqDto.getGasStationNo());

        //유저의 주유량
        double userGasAmount = userRecordService.calTodayUserGasAmount(userGasRecordReqDto, gasStation);

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
                (long) userGasAmount,
                usersSavingPrice);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponseDto.toResponse(DefaultCode.SAVE_USER_GAS_RECORD));
    }

    @GetMapping("/user/gas-record")
    public ResponseEntity<CommonResponseDto> getUserGasRecord() {
        List<UserGasRecordResDto> userGasRecords = userRecordService.getUserRecords();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.GET_USER_GAS_RECORDS, userGasRecords));
    }
    
    @GetMapping("/user/eco-record")
    public ResponseEntity<CommonResponseDto> getUserEcoRecord() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_FIND_USER_ECO_RECORD, userRecordService.calMonthUserEcoPrice()));
    }

    @GetMapping("/user/gas-record/month")
    public ResponseEntity<CommonResponseDto> getUsersMonthRecord() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_FIND_USER_RECORDS_MONTH, userRecordService.getUsersRecordPerMonth()));
    }


       
}
