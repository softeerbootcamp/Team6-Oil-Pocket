package com.kaspi.backend.dao;

import com.kaspi.backend.domain.GasDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasDetailDao extends CrudRepository<GasDetail, Long> {

}
