package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasStation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface GasStationDao extends CrudRepository<GasStation, Long> {

    @Query("select * from gas_station where address = :address and brand = :brand")
    Optional<GasStation> findByAddressAndBrand(@Param("address") String address, @Param("brand") String brand);

    @Query("select * from gas_station where address like :address and brand = :brand")
    Optional<GasStation> findByLikeAddressAndBrand(@Param("address") String address, @Param("brand") String brand);
}
