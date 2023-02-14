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


    private final HttpSessionService httpSessionService;
    private final UserGasRecordDao userGasRecordDao;
    private final GasStationDao gasStationDao;



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

    @GetMapping("/user/gas-record")
    public ResponseEntity<CommonResponseDto> getUserGasRecord() {
        User user = httpSessionService.getUserFromSession();
        List<UserGasRecord> matchingUserGasRecords = userGasRecordDao.findGasRecordListByUserId(user.getUserNo());
        List<UserGasRecordResDto> list = new ArrayList<>();
        for (UserGasRecord userGasRecord : matchingUserGasRecords) {
            Optional<GasStation> gasStation = gasStationDao.findById(userGasRecord.getGasStationNo());
            if(gasStation.isEmpty()){
                //TODO 예외처리
            }
            list.add(UserGasRecordResDto.toUserGasRecordResDto(userGasRecord, gasStation.get()));
        }


        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponseDto.toResponse(DefaultCode.GET_USER_GAS_RECORDS,list));
    }
}
