package com.kaspi.backend.service;

import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasStation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public class NomalGasDetailCallback implements GasDetailCallback{
    @Override
    public List<GasDetail> makeGasDetailAndSaveToDB(GasStation gasStation, CrudRepository crudRepository, String[] attribute, LocalDate date) {
        List<GasDetail> gasDetailList = GasDetail.parseListGasDetail(gasStation, attribute, date);
        for (GasDetail gasDetail : gasDetailList) {
            crudRepository.save(gasDetail);
        }
        return gasDetailList;
    }
}
