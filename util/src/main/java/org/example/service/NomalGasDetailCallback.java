package org.example.service;

import org.example.dao.GasStationDataDao;
import org.example.domain.GasDetail;

import java.util.List;

public class NomalGasDetailCallback implements GasDetailCallback{
    @Override
    public List<GasDetail> makeGasDetailAndSaveToDB(GasStationDataDao gasStationDataDao, String[] attribute) {
        List<GasDetail> gasDetailList = GasDetail.parseListGasDetail(attribute);
        gasStationDataDao.insertGasDetails(gasDetailList);
        return gasDetailList;
    }
}
