package org.example.service;

import org.example.dao.GasStationDataDao;
import org.example.domain.GasDetail;

import java.util.ArrayList;
import java.util.List;

public class LpgDetailCallback implements GasDetailCallback {
    @Override
    public List<GasDetail> makeGasDetailAndSaveToDB(GasStationDataDao gasStationDataDao, String[] attribute) {
        List<GasDetail> list = new ArrayList<>();
        GasDetail gasDetail = GasDetail.parseLpgGasDetail(attribute);
        gasStationDataDao.insertGasDetail(gasDetail);
        list.add(gasDetail);
        return list;
    }
}
