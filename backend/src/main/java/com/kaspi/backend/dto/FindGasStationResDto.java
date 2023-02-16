package com.kaspi.backend.dto;

import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.enums.GasBrand;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Objects;

@Builder
@Getter
@RedisHash("gasStation")
public class FindGasStationResDto implements Serializable {
    private Long stationNo;
    private String area;
    private String name;
    private String address;
    private String brand;

    public static FindGasStationResDto toFindDto(GasStationDto gasStationDto) {
        return FindGasStationResDto.builder()
                .stationNo(gasStationDto.getStationNo())
                .area(gasStationDto.getArea())
                .name(gasStationDto.getName())
                .address(gasStationDto.getAddress())
                .brand(GasBrand.getImgByDbName(gasStationDto.getBrand()))
                .build();
    }

    @Override
    public boolean equals(Object o) {
        FindGasStationResDto opposite = (FindGasStationResDto) o;
        return this.stationNo.equals(opposite.stationNo);
    }

}
