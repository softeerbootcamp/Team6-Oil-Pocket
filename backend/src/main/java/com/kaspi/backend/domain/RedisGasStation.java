package com.kaspi.backend.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashMap;

@RedisHash("gasStation")
@Builder
@Getter
public class RedisGasStation {
    @Id
    private Long stationNo;
    private String area;
    private String name;
    private String address;
    private String brand;

}
