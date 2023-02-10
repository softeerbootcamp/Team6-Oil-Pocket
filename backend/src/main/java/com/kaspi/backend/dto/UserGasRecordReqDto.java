package com.kaspi.backend.dto;

import com.kaspi.backend.enums.GasType;
import lombok.Getter;

import java.util.Date;

@Getter
public class UserGasRecordReqDto {

    private GasType gasType;
    private Long refuelingPrice;
    private Long gasStationNo;

    private Date chargeDate;
}
