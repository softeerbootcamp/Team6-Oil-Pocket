package com.kaspi.backend.domain;

import com.kaspi.backend.enums.GasType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GasDetail {
    private static final int ADDRESS = 4;
    private static final int PREMIUM_GASOLINE = 6;
    private static final int GASOLINE = 7;
    private static final int DIESEL = 8;
    private static final int LPG = 6;
    private String address;
    private String brand;
    private int price;
    private GasType gasType;
    private LocalDate date;

    public GasDetail(String address, String brand, int price, GasType gasType, LocalDate date) {
        this.address = address;
        this.brand = brand;
        this.price = price;
        this.gasType = gasType;
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
        list.add(new GasDetail(attribute[3], attribute[4], Integer.valueOf(attribute[PREMIUM_GASOLINE]), GasType.PREMIUM_GASOLINE, date));
        list.add(new GasDetail(attribute[3], attribute[4], Integer.valueOf(attribute[GASOLINE]), GasType.GASOLINE, date));
        list.add(new GasDetail(attribute[3], attribute[4], Integer.valueOf(attribute[DIESEL]), GasType.DIESEL, date));
        return list;
    }

    public static GasDetail parseLpgGasDetail(String[] attribute, LocalDate date) {
        return new GasDetail(attribute[3], attribute[4], Integer.valueOf(attribute[LPG]), GasType.LPG, date);
    }
}
