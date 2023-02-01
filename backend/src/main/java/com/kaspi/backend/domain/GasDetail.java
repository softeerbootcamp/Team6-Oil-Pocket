package com.kaspi.backend.domain;

import com.kaspi.backend.enums.GasType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GasDetail {
    private static final int GAS_STATION = 0;
    private static final int PREMIUM_GASOLINE = 6;
    private static final int GASOLINE = 7;
    private static final int DIESEL = 8;
    private static final int LPG = 6;
    private String gasStationNo;
    private int price;
    private GasType gasType;
    private LocalDate date;

    public GasDetail(String gasStationNo, int price, GasType gasType, LocalDate date) {
        this.gasStationNo = gasStationNo;
        this.price = price;
        this.gasType = gasType;
        this.date = date;
    }

    public String getGasStationNo() {
        return gasStationNo;
    }

    public void setGasStationNo(String gasStationNo) {
        this.gasStationNo = gasStationNo;
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

    public static List<GasDetail> parseListGasDetail(String[] attribute, LocalDate date) {
        List<GasDetail> list = new ArrayList<>();
        list.add(new GasDetail(attribute[GAS_STATION], floorPrice(attribute[PREMIUM_GASOLINE]), GasType.PREMIUM_GASOLINE, date));
        list.add(new GasDetail(attribute[GAS_STATION], floorPrice(attribute[GASOLINE]), GasType.GASOLINE, date));
        list.add(new GasDetail(attribute[GAS_STATION], floorPrice(attribute[DIESEL]), GasType.DIESEL, date));
        return list;
    }

    public static GasDetail parseLpgGasDetail(String[] attribute, LocalDate date) {
        return new GasDetail(attribute[GAS_STATION], floorPrice(attribute[LPG]), GasType.LPG, date);
    }

    private static int floorPrice(String price) {
        return Integer.valueOf(price.substring(0, price.indexOf(".")));
    }
}
