package org.example.dao;

import org.example.domain.GasDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasDetailDao extends CrudRepository<GasDetail, Long> {

}
