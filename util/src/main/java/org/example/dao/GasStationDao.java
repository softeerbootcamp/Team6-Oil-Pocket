package org.example.dao;

import org.example.domain.GasStation;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GasStationDao extends CrudRepository<GasStation, Long> {

    @Query("select * from gas_station where address like :address and brand = :brand")
    public Optional<GasStation> findByAddressAndBrand(@Param("address") String address, @Param("brand") String brand);

    @Query("select * from gas_station")
    Optional<List<GasStation>> findAllGasStation();
}
