package org.example;

import org.example.domain.GasStation;

import java.io.*;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileUploadService {

    public static final String PATH_PREFIX = "/Users/historyData/";
    private static final String PATH_SUFFIX = ".csv";
    private static final String OIL_FILE = "과거_판매가격(주유소)";
    private static final String LPG_FILE = "과거_판매가격(충전소)";
    private final Map<String, GasStation> gasStationInfos = new HashMap<>();
    public FileUploadService() {}

    public String removeDoubleQuotationMarks(String line) {
        return line.replaceAll("\"", "");
    }
}
