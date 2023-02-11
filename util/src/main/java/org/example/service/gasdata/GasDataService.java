package org.example.service.gasdata;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dao.GasDetailDao;
import org.example.dao.GasStationDao;
import org.example.domain.GasDetail;
import org.example.domain.GasStation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GasDataService {
    private final GasStationDao gasStationDao;
    private final GasDetailDao gasDetailDao;
    private final Map<String, GasStation> gasStationInfos = new HashMap<>();

    @Transactional
    public void insertGasInfo(final String fileName, GasDataCallback callback) throws IOException, InterruptedException {
        try {
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
                    //디비에 이미 주유소 정보가 있다면 넣지 않음
                    Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand(gasStation.getAddress(), gasStation.getBrand());
                    saveGasStationIfNotExists(key, gasStation, optionalGasStation);
                }
                GasStation gasStation = gasStationInfos.get(key);
                System.out.println("sataion_no: " + gasStation.getStationNo());
                List<GasDetail> gasDetails = callback.makeGasDetailAndSaveToDB(gasStation, gasDetailDao, attribute, date);
            }
            file.delete();
        } catch (FileNotFoundException e) {
            log.debug("FiilNotFoundExeption");
            insertGasInfo(fileName, callback);
        }

    }

    private void saveGasStationIfNotExists(String key, GasStation gasStation, Optional<GasStation> optionalGasStation) {
        if (optionalGasStation.isEmpty()) {
            gasStationDao.save(gasStation);
            gasStationInfos.put(key, gasStation);
            return;
        }
        gasStationInfos.put(key, optionalGasStation.get());
    }

    public String makeCacheKey(String[] attribute) {
        return makeEndAddress(attribute[3]) + ":" + attribute[4];
    }

    private String makeEndAddress(String address) {
        String[] temp = address.split(" |\\(");
        int idx = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i].matches(".*로$") || temp[i].matches(".*길$")) {
                idx = i;
                break;
            }
        }
        String result = "";
        for (int i = idx; i < temp.length; i++) {
            if (temp[i].contains(")")) {
                break;
            }
            result += temp[i] + " ";
        }
        return result.trim();
    }
}
