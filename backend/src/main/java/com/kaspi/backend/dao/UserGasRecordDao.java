package com.kaspi.backend.dao;

import com.kaspi.backend.domain.UserGasRecord;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserGasRecordDao extends CrudRepository<UserGasRecord, Long> {
    @Query("select * from user_gas_record where user_no = :userNo and month(charge_date) = month(:date) and year(charge_date) = year(:date)")
    Optional<List<UserGasRecord>> findByMonthOfNow(@Param("user_no") Long userNo, @Param("date") LocalDate date);
}

