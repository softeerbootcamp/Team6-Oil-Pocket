package com.kaspi.backend.dao;

import com.kaspi.backend.domain.EcoRecord;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserGasRecordDao extends CrudRepository<UserGasRecord, Long> {
    @Query("select charge_date,gas_station_no, record_gas_type ,record_gas_amount ,refueling_price ,saving_price  from user_gas_record ugr " +
            "where user_no = :userNo;")
    List<UserGasRecord> findGasRecordListByUserId(@Param("userNo") Long userNo);
    @Query("select * from user_gas_record where user_no = :userNo and month(charge_date) = month(:date) and year(charge_date) = year(:date)")
    Optional<List<UserGasRecord>> findByMonthOfNow(@Param("user_no") Long userNo, @Param("date") LocalDate date);

    @Query("select u.user_no, gender, age, refueling_price, saving_price, PERCENT_RANK() over (order by saving_price desc) as per_rank from users u JOIN " +
            "(select user_no, sum(refueling_price) refueling_price, sum(saving_price) saving_price from user_gas_record ugr where month(charge_date) = month(:date) and year(charge_date) = year(:date) group by user_no) ugr " +
            "on u.user_no = ugr.user_no where gender = :gender and age = :age")
    Optional<List<EcoRecord>> findSavingPriceByGenderAndAge(@Param("gender") Gender gender, @Param("age") Age age, @Param("date") LocalDate date);
}
