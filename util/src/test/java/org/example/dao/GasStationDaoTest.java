package org.example.dao;

import org.assertj.core.api.Assertions;
import org.example.domain.GasStation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GasStationDaoTest {

    @Autowired
    GasStationDao gasStationDao;
    @Test
    @DisplayName("selectAll 성공 테스트")
    void selectAll() {
        List<GasStation> gasStations = gasStationDao.findAllGasStation().orElseThrow(() -> new RuntimeException());
        Assertions.assertThat(gasStations.size()).isEqualTo(1);
        //항상 성공하게 하려면 어떻게 해야할까..?
    }

}