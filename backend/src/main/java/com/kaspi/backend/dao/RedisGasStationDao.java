package com.kaspi.backend.dao;

import com.kaspi.backend.domain.RedisGasStation;
import org.springframework.data.repository.CrudRepository;

public interface RedisGasStationDao extends CrudRepository<RedisGasStation, Long> {
}
