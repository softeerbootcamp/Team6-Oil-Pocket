package com.kaspi.backend.domain;

import java.util.List;

public class GasStationDto {
    private String area;
    private String name;
    private String address;
    private String brand;
    private boolean self;
    private List<GasDetailDto> details;

    public GasStationDto(String area, String name, String address, String brand, boolean self, List<GasDetailDto> details) {
        this.area = area;
        this.name = name;
        this.address = address;
        this.brand = brand;
        this.self = self;
        this.details = details;
    }
    public static GasStationDto newInstance(GasStation gasStation, List<GasDetailDto> list) {
        return new GasStationDto(gasStation.getArea(), gasStation.getName(), gasStation.getAddress(), gasStation.getBrand(), gasStation.isSelf(), list);
    }
    public void addGasDetailList(List<GasDetailDto> gasDetaillist) {
        for (GasDetailDto gasDetailDto : gasDetaillist) {
            this.details.add(gasDetailDto);
        }
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

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public List<GasDetailDto> getDetails() {
        return details;
    }

    public void setDetails(List<GasDetailDto> details) {
        this.details = details;
    }
}
