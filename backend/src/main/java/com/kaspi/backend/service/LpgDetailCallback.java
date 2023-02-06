package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDataDao;
import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LpgDetailCallback implements GasDetailCallback{
    @Override
    public List<GasDetail> makeGasDetailAndSaveToDB(GasStation gasStation, CrudRepository crudRepository, String[] attribute, LocalDate date) {
        List<GasDetail> list = new ArrayList<>();
        GasDetail gasDetail = GasDetail.parseLpgGasDetail(gasStation, attribute, date);
        crudRepository.save(gasDetail);
        list.add(gasDetail);
        return list;
    }
}
