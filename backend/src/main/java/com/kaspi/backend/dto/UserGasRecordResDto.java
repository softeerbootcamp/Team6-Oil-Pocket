package com.kaspi.backend.dto;

import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.enums.GasBrand;
import lombok.Builder;
import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

@Getter
@Builder
public class UserGasRecordResDto implements Comparable<UserGasRecordResDto> {

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


    //날짜를 기준으로 정렬 내림차순 정렬(최근것 우선)
    @Override
    public int compareTo(UserGasRecordResDto o) {
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("yyyy.MM.dd").parse(this.getChargeDate());
            date2 = new SimpleDateFormat("yyyy.MM.dd").parse(o.getChargeDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date2.compareTo(date1);
    }
}
