package com.kaspi.backend.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Getter
@Builder
@Table("food_image")
public class FoodImage {
    @Id
    private int food_no;
    private BigDecimal startPrice;
    private BigDecimal endPrice;
    private String imageUrl;

}
