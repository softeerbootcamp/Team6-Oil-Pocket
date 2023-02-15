package com.kaspi.backend.dto;

import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.enums.GasBrand;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class UserGasRecordResDto implements Comparable<UserGasRecordResDto> {

    private LocalDate chargeDate;
    private String gasStationName;

    private String gasType;

    private String recordGasAmount;

    private String refuelingPrice;
    private String savingPrice;
    private String brand;

    public static UserGasRecordResDto toUserGasRecordResDto(UserGasRecord userGasRecord, GasStation gasStation) {
        return UserGasRecordResDto.builder()
                .chargeDate((userGasRecord.getChargeDate()))
                .gasStationName(gasStation.getName())
                .gasType(userGasRecord.getRecordGasType().getOpinetGasType())
                .recordGasAmount(userGasRecord.getRecordGasAmount() + "L")
                .refuelingPrice(userGasRecord.getRefuelingPrice() + "원")
                .savingPrice(userGasRecord.getSavingPrice() + "원")
                .brand(GasBrand.getImg(gasStation.getBrand())).build();
    }


    //날짜를 기준으로 정렬 내림차순 정렬(최근것 우선)
    @Override
    public int compareTo(UserGasRecordResDto o) {
        return o.chargeDate.compareTo(this.chargeDate);
    }
}
