package com.kaspi.backend.dto;

import com.kaspi.backend.dao.FoodImageDao;
import com.kaspi.backend.domain.FoodImage;
import com.kaspi.backend.domain.UserGasRecord;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEcoRecordResDto {
    private long refuelingPrice;
    private long ecoPrice;
    private String imageUrl;

    public static UserEcoRecordResDto newInstance(long refuelingPrice, long ecoPrice, FoodImage foodImage) {
        return new UserEcoRecordResDto(refuelingPrice, ecoPrice, foodImage.getImageUrl());
    }
}
