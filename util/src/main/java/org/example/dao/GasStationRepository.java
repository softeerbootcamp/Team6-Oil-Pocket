package org.example.dao;

import lombok.RequiredArgsConstructor;
import org.example.domain.GasDetail;
import org.example.domain.GasStation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class GasStationRepository {
    private final JdbcTemplate jdbcTemplate;

    public void batchInsert(List<GasStation> gasStations) {
        String sql = "INSERT INTO gas_station (area, name, address, brand, is_self) VALUES (?, ?, ?, ?, ?)";
        List<Object[]> batchArgs = new ArrayList<>();
        for (GasStation gasStation : gasStations) {
            Object[] row = new Object[]{
                    gasStation.getArea(),
                    gasStation.getName(),
                    gasStation.getAddress(),
                    gasStation.getBrand(),
                    gasStation.isSelf()
            };
            batchArgs.add(row);
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}
