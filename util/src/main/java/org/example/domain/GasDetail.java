package org.example.domain;

import org.example.enums.GasType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GasDetail {
    private static final int ADDRESS = 3;
    private static final int DATE = 4;
    private static final int BRAND = 5;
    private static final int PREMIUM_GASOLINE = 7;
    private static final int GASOLINE = 8;
    private static final int DIESEL = 9;
    private static final int LPG = 7;
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

    public static List<GasDetail> parseListGasDetail(String[] attribute) {
        List<GasDetail> list = new ArrayList<>();
        list.add(new GasDetail(attribute[ADDRESS], attribute[BRAND], Integer.valueOf(attribute[PREMIUM_GASOLINE]), GasType.PREMIUM_GASOLINE, LocalDate.parse(attribute[DATE], DateTimeFormatter.ofPattern("yyyyMMdd"))));
        list.add(new GasDetail(attribute[ADDRESS], attribute[BRAND], Integer.valueOf(attribute[GASOLINE]), GasType.GASOLINE, LocalDate.parse(attribute[DATE], DateTimeFormatter.ofPattern("yyyyMMdd"))));
        list.add(new GasDetail(attribute[ADDRESS], attribute[BRAND], Integer.valueOf(attribute[DIESEL]), GasType.DIESEL, LocalDate.parse(attribute[DATE], DateTimeFormatter.ofPattern("yyyyMMdd"))));
        return list;
    }

    public static GasDetail parseLpgGasDetail(String[] attribute) {
        return new GasDetail(attribute[ADDRESS], attribute[BRAND], Integer.valueOf(attribute[LPG]), GasType.LPG, LocalDate.parse(attribute[DATE], DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
}
