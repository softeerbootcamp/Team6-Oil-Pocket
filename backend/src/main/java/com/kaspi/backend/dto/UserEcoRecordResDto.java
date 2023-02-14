package com.kaspi.backend.dto;

import com.kaspi.backend.dao.FoodImageDao;
import com.kaspi.backend.domain.FoodImage;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEcoRecordResDto {
    private String userId;
    private Gender gender;
    private String age;
    private long refuelingPrice;
    private long myEcoPrice;
    private long averageEcoPrice;
    private String imageUrl;
    private double rankPercentage;

}
