package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDataDao;
import com.kaspi.backend.domain.GasDetail;

import java.time.LocalDate;
import java.util.List;

public class NomalGasDetailCallback implements GasDetailCallback{
    @Override
    public void makeGasDetailAndSaveToDB(GasStationDataDao gasStationDataDao, String[] attribute, LocalDate date) {
        List<GasDetail> gasDetailList = GasDetail.parseListGasDetail(attribute, date);
        gasStationDataDao.insertGasDetails(gasDetailList);
    }
}
