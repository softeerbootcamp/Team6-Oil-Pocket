package com.kaspi.backend.dao;

import com.kaspi.backend.domain.User;
import com.kaspi.backend.domain.UserGasRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGasRecordDao extends CrudRepository<UserGasRecord, Long> {}

