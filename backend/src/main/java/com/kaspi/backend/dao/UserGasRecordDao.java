package com.kaspi.backend.dao;

import com.kaspi.backend.domain.EcoRecord;
import com.kaspi.backend.domain.UserGasRecord;
import com.kaspi.backend.dto.UserGasRecordMonthResDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserGasRecordDao extends CrudRepository<UserGasRecord, Long> {
    @Query("select charge_date,gas_station_no, record_gas_type ,record_gas_amount ,refueling_price ,saving_price  from user_gas_record ugr " +
            "where user_no = :userNo \n" +
            "order by ugr.user_gas_record_no DESC")
    List<UserGasRecord> findGasRecordListByUserId(@Param("userNo") Long userNo);
    @Query("select * from user_gas_record where user_no = :userNo and month(charge_date) = month(:date) and year(charge_date) = year(:date)")
    Optional<List<UserGasRecord>> findByMonthOfNow(@Param("user_no") Long userNo, @Param("date") LocalDate date);

    @Query("select u.user_no, gender, age, refueling_price, saving_price, rank() over (order by saving_price desc) as saving_rank from users u JOIN " +
            "(select user_no, sum(refueling_price) refueling_price, sum(saving_price) saving_price from user_gas_record ugr where month(charge_date) = month(:date) and year(charge_date) = year(:date) group by user_no) ugr " +
            "on u.user_no = ugr.user_no where gender = :gender and age = :age")
    Optional<List<EcoRecord>> findSavingPriceByGenderAndAge(@Param("gender") Gender gender, @Param("age") Age age, @Param("date") LocalDate date);


    /**
     * 사용자의 주유기록에서 월별로 그룹화 하여 해당날짜, 월별 주유 금액, 전국 유가 평균값을 기반으로한 주유금액을 리턴합니다.
     */
    @Query("SELECT \n"
            + "  DATE_FORMAT(charge_date, '%Y.%m') AS month_date, \n"
            + "  SUM(refueling_price) AS total_refueling_price, \n"
            + "  SUM(saving_price)+SUM(refueling_price) AS total_national_avg_price\n"
            + "FROM \n"
            + "  user_gas_record ugr \n"
            + "WHERE \n"
            + "  charge_date BETWEEN DATE_SUB(NOW(), INTERVAL 12 MONTH) AND NOW()\n"
            + "  AND user_no = :userNo\n"
            + "GROUP BY \n"
            + "  month_date\n"
            + "ORDER BY \n"
            + "  month_date DESC")
    Optional<List<UserGasRecordMonthResDto>> findSumRecordGroupByMonth(@Param("userNo") Long userNo);
}
