package com.kaspi.backend.controller;

import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.dao.GasStationDao;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.dto.UserGasRecordReqDto;
import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.service.HttpSessionService;
import com.kaspi.backend.util.opinet.OpinentParser;
import com.kaspi.backend.util.opinet.OpinetRequest;
import com.kaspi.backend.util.response.CommonResponseDto;
import com.kaspi.backend.util.response.code.DefaultCode;
import com.kaspi.backend.util.response.code.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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


    @PostMapping("/v1/user/gas-record")
    public ResponseEntity<CommonResponseDto> postUserGasRecord(@RequestBody UserGasRecordReqDto userGasRecordReqDto) throws JSONException, IOException {

        Long gasStationNo = userGasRecordReqDto.getGasStationNo();
        Optional<GasStation> findGasStation = gasStationDao.findById(gasStationNo);
        if (findGasStation.isEmpty()) {
            throw new NoSuchElementException(ErrorCode.GAS_STATION_NOT_FOUND.getMessage());
        }
        GasStation gasStation = findGasStation.get();

        //주유소 주유량
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        Optional<Long> todayGasPrice = gasDetailDao.findTodayGasPrice(gasStationNo, userGasRecordReqDto.getGasType().name(), currentDate);
        if (todayGasPrice.isEmpty()) {
            //TODO 오늘 가격이 없기에 예외처리
        }
        Long userGasAmount = (long) Math.round(userGasRecordReqDto.getRefuelingPrice() / todayGasPrice.get());


        //전국 유가 평균 받기
        //TODO try catch
        JSONObject jsonAverageGasPrice = OpinetRequest.requestNowAverage();
        Map<GasType, Long> nationwideGasPricesAvg = OpinentParser.parseNowAverage(jsonAverageGasPrice);
        Long matchNationalGasPriceWithUser = nationwideGasPricesAvg.get(userGasRecordReqDto.getGasType());


        //사용자가 얼마만큼 손해/이익을 봤는지
        long savingAmount = userGasRecordReqDto.getRefuelingPrice() - matchNationalGasPriceWithUser * userGasAmount;


        User user = httpSessionService.getUserFromSession();
        UserGasRecord userGasRecord = UserGasRecord.builder().userNo(user.getUserNo())
                .gasStationNo(gasStationNo)
                .chargeDate(userGasRecordReqDto.getChargeDate())
                .refuelingPrice(userGasRecordReqDto.getRefuelingPrice())
                .recordGasAmount(userGasAmount) //주유소 주유량
                .savingAmount(savingAmount) // 사용자 전국 유가 평균값  가져오기
                .recordGasType(userGasRecordReqDto.getGasType()) // 여기서 변환할때 영어로 에러뜰려나?
                .build();


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponseDto.toResponse(DefaultCode.SUCCESS_SIGNUP));
    }


}
