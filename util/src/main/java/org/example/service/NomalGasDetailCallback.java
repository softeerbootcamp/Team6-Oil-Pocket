package org.example.service;

import org.example.domain.GasDetail;
import org.example.domain.GasStation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class NomalGasDetailCallback implements GasDetailCallback{
    @Override
    public List<GasDetail> makeGasDetailAndSaveToDB(GasStation gasStation, CrudRepository crudRepository, String[] attribute) {
        List<GasDetail> gasDetailList = GasDetail.parseListGasDetail(gasStation, attribute);
        for (GasDetail gasDetail : gasDetailList) {
            crudRepository.save(gasDetail);
        }
        return gasDetailList;
    }
}
