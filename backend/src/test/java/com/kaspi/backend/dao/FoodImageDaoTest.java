package com.kaspi.backend.dao;

import com.kaspi.backend.domain.FoodImage;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;


@DataJdbcTest
@ContextConfiguration(classes = {TestRedisConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class FoodImageDaoTest {

    @Autowired
    FoodImageDao foodImageDao;

    @Test
    @DisplayName("candy 사진을 가져오는 성공 테스트 : 999원")
    void findFoodImageByEcoPrice_candy_SUCCESS_999() {
        BigDecimal bigDecimal = BigDecimal.valueOf(999);
        Optional<FoodImage> foodImageByEcoPrice = foodImageDao.findFoodImageByEcoPrice(bigDecimal);
        FoodImage foodImage = foodImageByEcoPrice.get();
        Assertions.assertThat(foodImage.getFood_no()).isEqualTo(1);
    }
    @Test
    @DisplayName("coffe 사진을 가져오는 성공 테스트 : 1000원")
    void findFoodImageByEcoPrice_coffe_SUCCESS_1000() {
        BigDecimal bigDecimal = BigDecimal.valueOf(1000);
        Optional<FoodImage> foodImageByEcoPrice = foodImageDao.findFoodImageByEcoPrice(bigDecimal);
        FoodImage foodImage = foodImageByEcoPrice.get();
        Assertions.assertThat(foodImage.getFood_no()).isEqualTo(2);
    }
    @Test
    @DisplayName("coffe 사진을 가져오는 성공 테스트 : 4999원")
    void findFoodImageByEcoPrice_coffe_SUCCESS_4999() {
        BigDecimal bigDecimal = BigDecimal.valueOf(4999);
        Optional<FoodImage> foodImageByEcoPrice = foodImageDao.findFoodImageByEcoPrice(bigDecimal);
        FoodImage foodImage = foodImageByEcoPrice.get();
        Assertions.assertThat(foodImage.getFood_no()).isEqualTo(2);
    }
    @Test
    @DisplayName("hamburger 사진을 가져오는 성공 테스트 : 5000원")
    void findFoodImageByEcoPrice_hamburger_SUCCESS_5000() {
        BigDecimal bigDecimal = BigDecimal.valueOf(5000);
        Optional<FoodImage> foodImageByEcoPrice = foodImageDao.findFoodImageByEcoPrice(bigDecimal);
        FoodImage foodImage = foodImageByEcoPrice.get();
        Assertions.assertThat(foodImage.getFood_no()).isEqualTo(3);
    }
    @Test
    @DisplayName("hamburger 사진을 가져오는 성공 테스트 : 9999원")
    void findFoodImageByEcoPrice_hamburger_SUCCESS_9999() {
        BigDecimal bigDecimal = BigDecimal.valueOf(9999);
        Optional<FoodImage> foodImageByEcoPrice = foodImageDao.findFoodImageByEcoPrice(bigDecimal);
        FoodImage foodImage = foodImageByEcoPrice.get();
        Assertions.assertThat(foodImage.getFood_no()).isEqualTo(3);
    }
    @Test
    @DisplayName("rice 사진을 가져오는 성공 테스트 : 10000원")
    void findFoodImageByEcoPrice_riceSoup_SUCCESS_10000() {
        BigDecimal bigDecimal = BigDecimal.valueOf(10000);
        Optional<FoodImage> foodImageByEcoPrice = foodImageDao.findFoodImageByEcoPrice(bigDecimal);
        FoodImage foodImage = foodImageByEcoPrice.get();
        Assertions.assertThat(foodImage.getFood_no()).isEqualTo(4);
    }
    @Test
    @DisplayName("rice 사진을 가져오는 성공 테스트 : 19999원")
    void findFoodImageByEcoPrice_riceSoup_SUCCESS_19999() {
        BigDecimal bigDecimal = BigDecimal.valueOf(19999);
        Optional<FoodImage> foodImageByEcoPrice = foodImageDao.findFoodImageByEcoPrice(bigDecimal);
        FoodImage foodImage = foodImageByEcoPrice.get();
        Assertions.assertThat(foodImage.getFood_no()).isEqualTo(4);
    }
    @Test
    @DisplayName("chicken 사진을 가져오는 성공 테스트 : 20000원")
    void findFoodImageByEcoPrice_chicken_SUCCESS_20000() {
        BigDecimal bigDecimal = BigDecimal.valueOf(20000);
        Optional<FoodImage> foodImageByEcoPrice = foodImageDao.findFoodImageByEcoPrice(bigDecimal);
        FoodImage foodImage = foodImageByEcoPrice.get();
        Assertions.assertThat(foodImage.getFood_no()).isEqualTo(5);
    }
}