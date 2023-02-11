package org.example.service.fileupload;

import org.example.dao.GasDetailDao;
import org.example.dao.GasStationDao;
import org.example.domain.GasStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class FileUploadService {

    public static final String PATH_PREFIX = "/Users/historyData/";
    private static final String PATH_SUFFIX = ".csv";
    private static final String OIL_FILE = "과거_판매가격(주유소)1";
    private static final String LPG_FILE = "과거_판매가격(충전소)1";
    private final Map<String, GasStation> gasStationInfos = new HashMap<>();
    private final GasStationDao gasStationDao;
    private final GasDetailDao gasDetailDao;
    @Autowired
    public FileUploadService(GasStationDao gasStationDao, GasDetailDao gasDetailDao) {
        this.gasStationDao = gasStationDao;
        this.gasDetailDao = gasDetailDao;
    }
    @PostConstruct
    public void fileOpen() {
        File oilFile = new File(PATH_PREFIX + OIL_FILE + PATH_SUFFIX);
        fileRead(oilFile, new NomalGasDetailCallback());
        File lpgFile = new File(PATH_PREFIX + LPG_FILE + PATH_SUFFIX);
        fileRead(lpgFile, new LpgDetailCallback());
    }
    @Transactional
    public void fileRead(File file, GasDetailCallback callback) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "euc-kr"));
            br.readLine();
            br.readLine(); //두줄은 데이터 제목들이어서 건너뜁니다.

            String line = null;
            while ((line = br.readLine()) != null) {
                line = removeDoubleQuotationMarks(line);
                String[] attribute = line.split(",");
                if (attribute.length <= 1) break;
                attribute[3] = extractRoadNameAndBuildingNum(attribute[3]);
                String key = makeCacheKey(attribute);
                System.out.println(key);

                //키 값이 겹치는 지 확인
                if (!gasStationInfos.containsKey(key)) {
                    GasStation gasStation = GasStation.parseGasStation(attribute);
                    gasStationInfos.put(key, gasStation);

                    Optional<GasStation> optionalGasStation = gasStationDao.findByAddressAndBrand(gasStation.getAddress(), gasStation.getBrand());
                    if (optionalGasStation.isEmpty()) {
                        gasStationDao.save(gasStation);
                    }
                }
                GasStation gasStation = gasStationInfos.get(key);
                callback.makeGasDetailAndSaveToDB(gasStation, gasDetailDao, attribute);
            }
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String removeDoubleQuotationMarks(String line) {
        return line.replaceAll("\"", "");
    }

    private String extractRoadNameAndBuildingNum(String address) {
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

    public String makeCacheKey(String[] attribute) {
        return attribute[3] + ":" + attribute[5];
    }
}
