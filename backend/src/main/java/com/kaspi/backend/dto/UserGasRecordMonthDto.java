package com.kaspi.backend.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class UserGasRecordMonthDto {
    private String monthDate;
    private Long totalRefuelingPrice;
    private Long totalSavingPrice;
}
