package org.example.service.gasdata;

import org.example.domain.GasDetail;
import org.example.domain.GasStation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LpgGasDataCallback implements GasDataCallback{
    @Override
    public List<GasDetail> makeGasDetailAndSaveToDB(GasStation gasStation, CrudRepository crudRepository, String[] attribute, LocalDate date) {
        List<GasDetail> list = new ArrayList<>();
        GasDetail gasDetail = GasDetail.parseLpgGasDetail(gasStation, attribute, date);
        crudRepository.save(gasDetail);
        list.add(gasDetail);
        return list;
    }
}
