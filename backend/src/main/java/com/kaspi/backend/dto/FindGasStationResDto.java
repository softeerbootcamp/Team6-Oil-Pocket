package com.kaspi.backend.dto;

import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.enums.GasBrand;
import com.kaspi.backend.enums.GasType;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@RedisHash("gasStation")
public class FindGasStationResDto implements Serializable {
    private Long stationNo;
    private String area;
    private String name;
    private String address;
    private String brand;

    private Set<GasType> gasTypes;

    public static FindGasStationResDto toFindDto(GasStationDto gasStationDto) {
        return FindGasStationResDto.builder()
                .stationNo(gasStationDto.getStationNo())
                .area(gasStationDto.getArea())
                .name(gasStationDto.getName())
                .address(gasStationDto.getAddress())
                .brand(GasBrand.getImgByDbName(gasStationDto.getBrand()))
                .gasTypes(new HashSet<>())
                .build();
    }

    public void updateBrandToImage() {
        this.brand = GasBrand.getImgByDbName(this.brand);
    }

    @Override
    public boolean equals(Object o) {
        FindGasStationResDto opposite = (FindGasStationResDto) o;
        return this.stationNo.equals(opposite.stationNo);
    }

}
