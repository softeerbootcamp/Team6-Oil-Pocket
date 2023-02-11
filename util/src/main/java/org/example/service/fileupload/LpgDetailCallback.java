package org.example.service.fileupload;

import org.example.domain.GasDetail;
import org.example.domain.GasStation;
import org.example.service.fileupload.GasDetailCallback;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public class LpgDetailCallback implements GasDetailCallback {
    @Override
    public List<GasDetail> makeGasDetailAndSaveToDB(GasStation gasStation, CrudRepository crudRepository, String[] attribute) {
        List<GasDetail> list = new ArrayList<>();
        GasDetail gasDetail = GasDetail.parseLpgGasDetail(gasStation, attribute);
        crudRepository.save(gasDetail);
        list.add(gasDetail);
        return list;
    }
}
