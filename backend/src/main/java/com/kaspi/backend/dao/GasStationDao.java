package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.dto.FindGasStationResDto;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GasStationDao extends CrudRepository<GasStation, Long> {
    @Query("select * from gas_station where address = :address and brand = :brand")
    Optional<GasStation> findByAddressAndBrand(@Param("address") String address, @Param("brand") String brand);

    @Query("select * from gas_station where address like :address and brand = :brand")
    Optional<GasStation> findByLikeAddressAndBrand(@Param("address") String address, @Param("brand") String brand);


    @Query(" SELECT gr.station_no, gr.area, gr.name, gr.address, gr.brand FROM gas_station gr\n" +
            "join gas_detail gd on gr.station_no = gd .station_no \n" +
            "WHERE name LIKE  :requestName \n" +
            "and gd.gas_type = :requestGasType\n" +
            "and DATE(gd.created_date) = CURDATE()  \n" +
            "and  gd.price != 0")
    List<FindGasStationResDto> findGastationForGasSearch(@Param("requestName") String requestName, @Param("requestGasType") String requestGasType);
}
