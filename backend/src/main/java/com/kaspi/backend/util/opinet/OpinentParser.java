package com.kaspi.backend.util.opinet;

import com.kaspi.backend.enums.GasType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OpinentParser {

    public static Map<GasType,Long> parseNowAverage(JSONObject jsonObject) throws JSONException {
        Map<GasType, Long> averageOilData = new HashMap<>();
        JSONObject result = jsonObject.getJSONObject("RESULT");
        JSONArray oilsInfo = result.getJSONArray("OIL");
        for (int i = 0; i < oilsInfo.length(); i++) {
            JSONObject oilInfo = oilsInfo.getJSONObject(i);
            Optional<GasType> findGasType = GasType.getTypeFromOpinetData(oilInfo.getString("PRODNM"));
            if (findGasType.isPresent()) {
                Long price = Long.parseLong(oilInfo.getString("PRICE").split("\\.")[0]);
                averageOilData.put(findGasType.get(), price);
            }
        }
        return averageOilData;
    }

}
