package com.kaspi.backend.dto;

import com.kaspi.backend.enums.GasType;
import lombok.Builder;
import lombok.Getter;
import org.checkerframework.checker.index.qual.Positive;

import java.util.Date;

@Getter
@Builder
public class UserGasRecordReqDto {

    private GasType gasType;
    @Positive
    private Long refuelingPrice;
    private Long gasStationNo;
}
