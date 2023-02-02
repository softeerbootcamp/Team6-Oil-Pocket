package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasStation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GasStationDataDao {

    private final JdbcTemplate jdbcTemplate;

    public GasStationDataDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertGasStation(GasStation gasStation) {
        String query = "INSERT INTO gas_station(station_no, area, name, address, brand, is_self) " +
                "values (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, gasStation.getGasStationNo(), gasStation.getArea(), gasStation.getName(),
                gasStation.getAddress(), gasStation.getBrand(), gasStation.getIsSelf());
    }
    public void insertGasDetails(List<GasDetail> list) {
        for (GasDetail gasDetail : list) {
            insertGasDetail(gasDetail);
        }
    }
    public void insertGasDetail(GasDetail gasDetail) {
        String query = "INSERT INTO gas_detail(station_no, gas_type, price, created_date)" +
                "values (?, ?, ?, ?)";
        jdbcTemplate.update(query, gasDetail.getGasStationNo(), gasDetail.getGasType().name(), gasDetail.getPrice(), gasDetail.getDate());
    }
}
