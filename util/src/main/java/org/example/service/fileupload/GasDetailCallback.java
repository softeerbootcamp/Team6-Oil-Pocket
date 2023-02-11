package org.example.service.fileupload;


import org.example.domain.GasDetail;
import org.example.domain.GasStation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GasDetailCallback {

    List<GasDetail> makeGasDetailAndSaveToDB(GasStation gasStation, CrudRepository crudRepository, String[] attribute);
}
