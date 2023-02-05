package com.kaspi.backend.service;

import com.kaspi.backend.dao.GasStationDataDao;
import com.kaspi.backend.domain.GasDetail;
import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStation;
import com.kaspi.backend.domain.GasStationDto;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GasDataService {
    private final GasStationDataDao gasStationDataDao;
    private final Map<String, GasStation> gasStationInfos = new HashMap<>();
    private final Map<String, GasStationDto> cacheMap = new ConcurrentHashMap<>();

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
            attribute[3] = makeEndAddress(attribute[3]);
            String key = makeCacheKey(attribute);
            System.out.println(key);

            //가스 도로명+브랜드 겹치는거 있는지 체크
            if (!gasStationInfos.containsKey(key)) {
                GasStation gasStation = GasStation.parseGasStation(attribute);
                gasStationInfos.put(key, gasStation);
                gasStationDataDao.insertGasStation(gasStation);
            }
            GasStation gasStation = gasStationInfos.get(key);
            attribute[0] = gasStationInfos.get(key).getGasStationNo();
            List<GasDetail> gasDetails = callback.makeGasDetailAndSaveToDB(gasStationDataDao, attribute, date);
            if (cacheMap.containsKey(key)) {
                GasStationDto gasStationDto = cacheMap.get(key);
                gasStationDto.addGasDetailList(GasDetailDto.newDtoList(gasDetails));
                continue;
            }
            cacheMap.put(key, GasStationDto.newInstance(gasStationInfos.get(key), GasDetailDto.newDtoList(gasDetails)));
        }
        file.delete();
    }

    public String makeCacheKey(String[] attribute) {
        return makeEndAddress(attribute[3]) + ":" + attribute[4];
    }

    private String makeEndAddress(String address) {
        String[] temp = address.split(" ");
        int idx = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].contains("로") || temp[i].contains("길")) {
                idx = i;
                break;
            }
        }
        String result = "";
        for (int i = idx; i < temp.length; i++) {
            if (temp[i].contains("(")) {
                break;
            }
            result += temp[i] + " ";
        }
        return result.trim();
    }

    public void initCache() {
        cacheMap.clear();
    }
}
