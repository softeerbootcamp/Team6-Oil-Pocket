package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@AllArgsConstructor
public class StationAndDetailSet {
    private GasStation gasStation;
    private String[] attribute;
    private LocalDate date;
    private boolean isLpg;
}
