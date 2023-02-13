package com.kaspi.backend.dao;

import com.kaspi.backend.domain.FoodImage;
import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.dto.UserEcoRecordResDto;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface FoodImageDao extends CrudRepository<FoodImage, Long> {
    @Query("select food_no, start_price, end_price, image_url from food_image where start_price <= :ecoPrice and end_price > :ecoPrice ")
    Optional<FoodImage> findFoodImageByEcoPrice(@Param("ecoPrice") BigDecimal ecoPrice);
}
