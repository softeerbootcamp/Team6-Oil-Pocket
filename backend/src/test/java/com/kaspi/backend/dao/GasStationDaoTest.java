package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.util.config.RedisConfiguration;
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

import java.util.Optional;

@DataJdbcTest
@ContextConfiguration(classes = {RedisConfiguration.class, TestRedisConfiguration.class})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class GasStationDaoTest {

    @Autowired
    GasStationDao gasStationDao;
    @Test
    @DisplayName("save 테스트")
    void saveTest() {
        String[] input = new String[]{"A0000035", "서울 종로구", "㈜지에스이앤알 평창주유소", "서울 종로구 지우로 9999 (평창동)"
                , "현대오일뱅크", "셀프", "1899", "1659", "1759", "0"};
        GasStation gasStation = GasStation.parseGasStation(input);
        gasStationDao.save(gasStation);
    }

    @Test
    @DisplayName("gasStation findByAddressAndBrand 테스트")
    void findByAddressAndBrandTest() {
        String[] input = new String[]{"A0000035", "서울 종로구", "㈜지에스이앤알 평창주유소", "서울 종로구 지우로 9999 (평창동)"
                , "현대오일뱅크", "셀프", "1899", "1659", "1759", "0"};
        GasStation gasStation = GasStation.parseGasStation(input);
        gasStationDao.save(gasStation);
        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand("%지우로 9999%", "현대오일뱅크");
        Assertions.assertThat(optionalGasStation.get()).usingRecursiveComparison().isEqualTo(gasStation);
    }
    @Test
    @DisplayName("gasStation findByAddressAndBrand null 테스트")
    void findByAddressAndBrandTest2() {
        String[] input = new String[]{"A0000035", "서울 종로구", "㈜지에스이앤알 평창주유소", "서울 종로구 지우로 9999 (평창동)"
                , "현대오일뱅크", "셀프", "1899", "1659", "1759", "0"};
        GasStation gasStation = GasStation.parseGasStation(input);
        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand("지우로 9999", "현대오일뱅크");
        Assertions.assertThat(optionalGasStation.isEmpty()).isEqualTo(true);
    }

}