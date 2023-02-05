package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDataDao;
import com.kaspi.backend.domain.GasDetail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LpgDetailCallback implements GasDetailCallback{
    @Override
    public List<GasDetail> makeGasDetailAndSaveToDB(GasStationDataDao gasStationDataDao, String[] attribute, LocalDate date) {
        List<GasDetail> list = new ArrayList<>();
        GasDetail gasDetail = GasDetail.parseLpgGasDetail(attribute, date);
        gasStationDataDao.insertGasDetail(gasDetail);
        list.add(gasDetail);
        return list;
    }
}
