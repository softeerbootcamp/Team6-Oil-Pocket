package com.kaspi.backend.dto;

import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.enums.GasBrand;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class UserGasRecordResDto {

    private String chargeDate;
    private String gasStationName;

    private String gasType;

    private String recordGasAmount;

    private String refuelingPrice;
    private String savingPrice;
    private String brand;

    public static UserGasRecordResDto toUserGasRecordResDto(UserGasRecord userGasRecord, GasStation gasStation) {
        return UserGasRecordResDto.builder()
                .chargeDate(changeDate(userGasRecord.getChargeDate()))
                .gasStationName(gasStation.getName())
                .gasType(userGasRecord.getRecordGasType().getOpinetGasType())
                .recordGasAmount(userGasRecord.getRecordGasAmount() + "L")
                .refuelingPrice(userGasRecord.getRefuelingPrice() + "원")
                .savingPrice(userGasRecord.getSavingPrice() + "원")
                .brand(GasBrand.getImg(gasStation.getBrand())).build();
    }

    private static String changeDate(Date date) {
        return date.toString().replace("-", ".");
    }

}
