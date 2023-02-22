package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.domain.GasDetail;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GasDetailRepository {
    private final JdbcTemplate jdbcTemplate;

    public void batchInsert(List<GasDetail> gasDetails) {
        String sql = "INSERT INTO gas_detail (station_no, gas_type, price, created_date) VALUES (?, ?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (GasDetail gasDetail : gasDetails) {
            Object[] row = new Object[]{
                    gasDetail.getStationNo(),
                    gasDetail.getGasType().name(),
                    gasDetail.getPrice(),
                    gasDetail.getDate()
            };
            batchArgs.add(row);
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}
