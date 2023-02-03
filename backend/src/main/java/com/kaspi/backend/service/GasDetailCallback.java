package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDataDao;

import java.time.LocalDate;

public interface GasDetailCallback {

    void makeGasDetailAndSaveToDB(GasStationDataDao gasStationDataDao, String[] attribute, LocalDate date);
}
