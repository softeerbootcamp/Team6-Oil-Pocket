package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasDetail;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GasDetailDao extends CrudRepository<GasDetail, Long> {

    ;
    @Query("SELECT price from gas_detail  where station_no = :stationNo and gas_type = :gasType and created_date = :todayDate")
    Optional<Long> findTodayGasPrice(@Param("stationNo") Long stationNo, @Param("gasType") String gasType,@Param("todayDate")String todayDate);

}
