package com.kaspi.backend.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Builder
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
    public static GasStationDto newInstance(String name, GasStation gasStation, List<GasDetailDto> list) {
        return new GasStationDto(gasStation.getArea(), name, gasStation.getAddress(), gasStation.getBrand(), gasStation.isSelf(), list);
    }
    public void addGasDetailDtoList(List<GasDetailDto> gasDetailDtolist) {
        for (GasDetailDto gasDetailDto : gasDetailDtolist) {
            this.details.add(gasDetailDto);
        }
    }
}