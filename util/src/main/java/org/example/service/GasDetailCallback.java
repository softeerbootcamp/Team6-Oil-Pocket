package org.example.service;


import org.example.dao.GasStationDataDao;
import org.example.domain.GasDetail;

import java.util.List;

public interface GasDetailCallback {

    List<GasDetail> makeGasDetailAndSaveToDB(GasStationDataDao gasStationDataDao, String[] attribute);
}
