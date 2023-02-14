package com.kaspi.backend.dao;

import com.kaspi.backend.domain.User;
import com.kaspi.backend.domain.UserGasRecord;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserGasRecordDao extends CrudRepository<UserGasRecord, Long> {
    @Query("select charge_date,gas_station_no, record_gas_type ,record_gas_amount ,refueling_price ,saving_price  from user_gas_record ugr " +
            "where user_no = :userNo;")
    List<UserGasRecord> findGasRecordListByUserId(@Param("userNo") Long userNo);
}

