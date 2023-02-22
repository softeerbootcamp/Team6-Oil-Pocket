package org.example.service.gasdata;

import org.example.domain.GasDetail;
import org.example.domain.GasStation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface GasDataCallback {
    List<GasDetail> makeGasDetailAndSaveToDB(GasStation gasStation, CrudRepository crudRepository, String[] attribute, LocalDate date);
}
