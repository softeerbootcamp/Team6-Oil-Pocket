package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDataDao;
import com.kaspi.backend.domain.GasDetail;

import java.time.LocalDate;
import java.util.List;

public interface GasDetailCallback {

    List<GasDetail> makeGasDetailAndSaveToDB(GasStationDataDao gasStationDataDao, String[] attribute, LocalDate date);
}
