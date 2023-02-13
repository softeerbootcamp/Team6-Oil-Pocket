package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasDetail;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GasDetailDao extends CrudRepository<GasDetail, Long> {
    @Query("SELECT price from gas_detail  where station_no = :stationNo and gas_type = :gasType and created_date = :todayDate")
    Optional<Long> findTodayGasPrice(@Param("stationNo") Long stationNo, @Param("gasType") String gasType, @Param("todayDate")String todayDate);

    @Query("select * from gas_detail where station_no = :stationNo and created_date = :date")
    Optional<List<GasDetail>> findByStationNoAndDate(@Param("stationNo") Long stationNo, @Param("date") LocalDate date);

    @Query("select * from gas_detail where station_no = :stationNo and created_date >= date_add( :date, interval -1 month)")
    Optional<List<GasDetail>> findByStationAndOneMonth(@Param("stationNo") Long stationNo, @Param("date") LocalDate date);
}
