package com.kaspi.backend.dto;

import lombok.Getter;

@Getter
public class UserGasRecordMonthResDto {
    private String monthDate;
    private Long totalRefuelingPrice;
    private Long totalNationalAvgPrice;
}
