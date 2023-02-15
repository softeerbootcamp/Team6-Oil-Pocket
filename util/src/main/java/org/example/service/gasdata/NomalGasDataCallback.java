package org.example.service.gasdata;

import org.example.domain.GasDetail;
import org.example.domain.GasStation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public class NomalGasDataCallback implements GasDataCallback{
    @Override
    public List<GasDetail> makeGasDetailAndSaveToDB(GasStation gasStation, CrudRepository crudRepository, String[] attribute, LocalDate date) {
        List<GasDetail> gasDetailList = GasDetail.parseListGasDetail(gasStation, attribute, date);
        for (GasDetail gasDetail : gasDetailList) {
            crudRepository.save(gasDetail);
        }
        return gasDetailList;
    }
}
