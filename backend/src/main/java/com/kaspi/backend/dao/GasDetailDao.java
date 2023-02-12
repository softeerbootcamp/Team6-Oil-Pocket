package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasDetail;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GasDetailDao extends CrudRepository<GasDetail, Long> {
    @Query("select * from gas_detail where station_no = :stationNo and created_date = :date")
    Optional<List<GasDetail>> findByStationNoAndDate(@Param("stationNo") Long stationNo, @Param("date") LocalDate date);
}
