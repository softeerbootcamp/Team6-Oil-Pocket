package com.kaspi.backend.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@NoArgsConstructor
@AllArgsConstructor
public class GasStation {
    @Id
    private Long stationNo;
    private String area;
    private String name;
    private String address;
    private String brand;
    @Column("is_self")
    private boolean self;

    public GasStation(String area, String name, String address, String brand, boolean self) {
        this.area = area;
        this.name = name;
        this.address = address;
        this.brand = brand;
        this.self = self;
    }

    public long getStationNo() {
        return stationNo;
    }

    public void setStationNo(long stationNo) {
        this.stationNo = stationNo;
    }

    public boolean isSelf() {
        return self;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean getIsSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public static GasStation parseGasStation(String[] attribute) {
        return new GasStation(attribute[1], attribute[2], attribute[3], attribute[4], isSelf(attribute[5]));
    }

    private static boolean isSelf(String attribute) {
        if (attribute.equals("셀프")) {
            return true;
        }
        return false;
    }
}
