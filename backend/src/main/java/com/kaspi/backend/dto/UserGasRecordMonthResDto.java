package com.kaspi.backend.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserGasRecordMonthResDto {
    private String monthDate;
    private Long totalRefuelingPrice;
    private Long totalNationalAvgPrice;
}
