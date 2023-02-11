package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasDetailDao;
import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.dto.UserGasRecordReqDto;
import com.kaspi.backend.util.response.code.ErrorCode;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRecordService {
    private final GasDetailDao gasDetailDao;


    /**
     *
     * 사용자로 부터 받은 주유금액, 유종과 주유소 정보로 얼마만큼 넣었는지 계산하는 로직
     */
    public Long calTodayUserGasAmount(UserGasRecordReqDto userGasRecordReqDto, GasStation gasStation) {
        Optional<Long> todayGasPrice = gasDetailDao.findTodayGasPrice(gasStation.getStationNo(),
                userGasRecordReqDto.getGasType().name(),
                GasDetail.getNowDateToStr());//오늘 날짜로 계산
        if (todayGasPrice.isEmpty()) {
            log.error("DB에 오늘날짜에 해당되는 주유 가격 정보가 존재하지 않음 가스타입:{}, 주유소PK:{}",userGasRecordReqDto.getGasType().name(),gasStation.getStationNo());
            throw new NoSuchElementException(ErrorCode.SQL_NOT_FOUND.getMessage());
        }
        Long userGasAmount = (long) Math.round(userGasRecordReqDto.getRefuelingPrice() / todayGasPrice.get());
        log.info("사용자가 주유한 가스타입:{}, 주유량:{}",userGasRecordReqDto.getGasType().name(),userGasAmount);
        return userGasAmount;
    }

    public Long calUserSavingAmount(Long userRefuelingPrice, Long userGasAmount, Long nationalAvgOilPrice) {
            return userRefuelingPrice - nationalAvgOilPrice * userGasAmount;
    }
}
