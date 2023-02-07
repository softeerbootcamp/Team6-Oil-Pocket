package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasStation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GasDetailDaoTest {
    @Autowired
    GasDetailDao gasDetailDao;

    @Autowired
    GasStationDao gasStationDao;
    @Test
    @DisplayName("gasDetailDao save() 테스트")
    void saveTest() {
        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand("%지우로 9999%", "현대오일뱅크");
        GasStation gasStation = optionalGasStation.get();

        String[] input = new String[]{"A0000035", "서울 종로구", "㈜지에스이앤알 평창주유소", "서울 종로구 지우로 9999 (평창동)"
                , "현대오일뱅크", "셀프", "1899", "1659", "1759", "0"};
        List<GasDetail> gasDetails = GasDetail.parseListGasDetail(gasStation, input, LocalDate.now());

        for (GasDetail gasDetail : gasDetails) {
            gasDetailDao.save(gasDetail);
        }
    }
}