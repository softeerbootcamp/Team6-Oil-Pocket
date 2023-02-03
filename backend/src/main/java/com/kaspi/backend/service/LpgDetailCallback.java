package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDataDao;
import com.kaspi.backend.domain.GasDetail;

import java.time.LocalDate;

public class LpgDetailCallback implements GasDetailCallback{
    @Override
    public void makeGasDetailAndSaveToDB(GasStationDataDao gasStationDataDao, String[] attribute, LocalDate date) {
        GasDetail gasDetail = GasDetail.parseLpgGasDetail(attribute, date);
        gasStationDataDao.insertGasDetail(gasDetail);
    }
}
