package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDataDao;
import com.kaspi.backend.domain.GasStation;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GasDataService {
    private final GasStationDataDao gasStationDataDao;
    private final List<String> cache = new ArrayList<>();

    public GasDataService(GasStationDataDao gasStationDataDao) {
        this.gasStationDataDao = gasStationDataDao;
    }

    public void insertGasInfo(final String fileName, GasDetailCallback callback) throws IOException, InterruptedException {
        Thread.sleep(4000);
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));
        String line = br.readLine();
        LocalDate date = LocalDate.now();
        while ((line = br.readLine()) != null) {
            String[] attribute = line.split(",");
            if (attribute.length <= 1) break;
            GasStation gasStation = GasStation.parseGasStation(attribute);
            if (!cache.contains(gasStation.getGasStationNo())) {
                System.out.println(line);
                cache.add(gasStation.getGasStationNo());
                gasStationDataDao.insertGasStation(gasStation);
            }
            callback.makeGasDetailAndSaveToDB(gasStationDataDao, attribute, date);
        }
        file.delete();
    }
}
