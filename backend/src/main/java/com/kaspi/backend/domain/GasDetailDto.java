package com.kaspi.backend.domain;

import com.kaspi.backend.enums.GasType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GasDetailDto {
    private GasType gasType;
    private int price;
    private LocalDate date;

    public GasDetailDto(GasType gasType, int price, LocalDate date) {
        this.gasType = gasType;
        this.price = price;
        this.date = date;
    }

    public static List<GasDetailDto> newDtoList(List<GasDetail> gasDetails) {
        List<GasDetailDto> list = new ArrayList<>();
        for (GasDetail gasDetail : gasDetails) {
            list.add(new GasDetailDto(gasDetail.getGasType(), gasDetail.getPrice(), gasDetail.getDate()));
        }
        return list;
    }
    public static GasDetailDto makeEmptyDetailDto(GasType gasType) {
        return new GasDetailDto(gasType, 0, LocalDate.now());
    }

    public static List<GasDetailDto> makeEmptyOilDetailDtoList() {
        List<GasDetailDto> list = new ArrayList<>();
        for (GasType value : GasType.values()) {
            if (value == GasType.LPG) continue;
            list.add(makeEmptyDetailDto(value));
        }
        return list;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public GasType getGasType() {
        return gasType;
    }

    public void setGasType(GasType gasType) {
        this.gasType = gasType;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
