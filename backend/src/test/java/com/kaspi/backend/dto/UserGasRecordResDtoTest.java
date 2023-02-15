package com.kaspi.backend.dto;

import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.enums.GasBrand;
import com.kaspi.backend.enums.GasType;
import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserGasRecordResDtoTest {

    @Test
    @DisplayName("사용자 주유 기록 dto로 변환하는 로직 테스트")
    void toUserGasRecordResDto() {
        //given
        UserGasRecord userGasRecord = UserGasRecord.builder()
                .chargeDate(LocalDate.now())
                .recordGasType(GasType.GASOLINE)
                .recordGasAmount(12L)
                .refuelingPrice(50000L)
                .savingPrice(120L).build();

        GasStation gasStation = GasStation.builder()
                .name("Test Gas Station")
                .brand(GasBrand.SK_GAS.getDbName()).build();

        //when
        UserGasRecordResDto dto = UserGasRecordResDto.toUserGasRecordResDto(userGasRecord, gasStation);
        //then
        assertEquals(userGasRecord.getRecordGasType().getOpinetGasType(), dto.getGasType());
        assertEquals(userGasRecord.getRecordGasAmount() + "L", dto.getRecordGasAmount());
        assertEquals(userGasRecord.getRefuelingPrice() + "원", dto.getRefuelingPrice());
        assertEquals(userGasRecord.getSavingPrice() + "원", dto.getSavingPrice());
        assertEquals(GasBrand.getImg(gasStation.getBrand()), dto.getBrand());
        assertEquals(gasStation.getName(), dto.getGasStationName());
        assertEquals(userGasRecord.getChargeDate().toString().replace("-", "."), dto.getChargeDate());
    }

    @Test
    @DisplayName("날짜 최신순 정렬 테스트")
    void compareTo() {
        UserGasRecordResDto recent = UserGasRecordResDto.builder()
                .chargeDate(LocalDate.now().plusMonths(1)).build();
        UserGasRecordResDto last = UserGasRecordResDto.builder()
                .chargeDate(LocalDate.now()).build();
        //when
        int result = recent.compareTo(last);
        //then
        assertTrue(result < 0);
    }
}