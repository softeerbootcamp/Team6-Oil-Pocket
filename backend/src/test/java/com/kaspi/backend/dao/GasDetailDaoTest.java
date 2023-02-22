package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.util.config.TestRedisConfiguration;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJdbcTest
@ContextConfiguration(classes = {TestRedisConfiguration.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class GasDetailDaoTest {
    @Autowired
    GasDetailDao gasDetailDao;

    @Autowired
    GasStationDao gasStationDao;

    @Test
    @DisplayName("gasDetailDao save() 성공 테스트")
    void saveTest() {
        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand("평창문화로 135", "현대오일뱅크");
        GasStation gasStation = optionalGasStation.get();

        String[] input = new String[]{"A0000035", "서울 종로구", "㈜지에스이앤알 평창주유소", "서울 종로구 지우로 9999 (평창동)"
                , "현대오일뱅크", "셀프", "1899", "1659", "1759", "0"};
        List<GasDetail> gasDetails = GasDetail.parseListGasDetail(gasStation, input, LocalDate.now());

        for (GasDetail gasDetail : gasDetails) {
            gasDetailDao.save(gasDetail);
        }
    }

    @Test
    @DisplayName("현재 날짜 기준 findByStationNoAndDate() 성공 테스트")
    void findByStationNoAndDateTest() {
        //1	서울 종로구	㈜지에스이앤알 평창주유소	평창문화로 135	현대오일뱅크	1
        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand("평창문화로 135", "현대오일뱅크");
        GasStation gasStation = optionalGasStation.get();

        Optional<List<GasDetail>> optionalGasDetails = gasDetailDao.findByStationNoAndDate(gasStation.getStationNo(), LocalDate.now());
        List<GasDetail> gasDetails = optionalGasDetails.get();
        SoftAssertions softAssertions = new SoftAssertions();
        for (GasDetail gasDetail : gasDetails) {
            softAssertions.assertThat(gasDetail.getStationNo()).isEqualTo(1);
            softAssertions.assertThat(gasDetail.getDate()).isEqualTo(LocalDate.now());
        }
        softAssertions.assertAll();
    }

    @Test
    @DisplayName("현재 날짜 기준 해당 주유소의 1달 로그 반환 성공 테스트")
    void findByStationAndOneMonth() {
        //given
        LocalDate date = LocalDate.of(2023, 2, 12);

        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand("평창문화로 135", "현대오일뱅크");
        GasStation gasStation = optionalGasStation.get();
        Optional<List<GasDetail>> optionalGasDetails = gasDetailDao.findByStationAndOneMonth(gasStation.getStationNo(), date);
        List<GasDetail> gasDetails = optionalGasDetails.get();
        SoftAssertions softAssertions = new SoftAssertions();
        for (GasDetail gasDetail : gasDetails) {
            softAssertions.assertThat(gasDetail.getStationNo()).isEqualTo(1);
            softAssertions.assertThat(gasDetail.getDate()).isBetween(LocalDate.now().minusMonths(1), LocalDate.now());
        }
        softAssertions.assertAll();
    }
    @Test
    @DisplayName("1달 주유소 정보 날짜 오름차순 정렬 테스트")
    void findByStationAndOneMonth_asc() {
        //given
        LocalDate date = LocalDate.of(2023, 2, 12);

        Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand("평창문화로 135", "현대오일뱅크");
        GasStation gasStation = optionalGasStation.get();
        Optional<List<GasDetail>> optionalGasDetails = gasDetailDao.findByStationAndOneMonth(gasStation.getStationNo(), date);
        List<GasDetail> gasDetails = optionalGasDetails.get();

        assertThat(gasDetails).isSortedAccordingTo(Comparator.comparing(GasDetail::getDate));
    }
}