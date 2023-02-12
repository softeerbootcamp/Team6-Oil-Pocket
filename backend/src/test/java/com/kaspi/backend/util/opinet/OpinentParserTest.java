package com.kaspi.backend.util.opinet;

import com.kaspi.backend.enums.GasType;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OpinentParserTest {

    @Test
    @DisplayName("주유소 유가 데이터 평균 MAP자료구조로 parse")
    void parseNowAverage() throws JSONException {
        //given
        JSONObject averageJson = new JSONObject("{\n" +
                "  \"RESULT\": {\n" +
                "    \"OIL\": [\n" +
                "      {\n" +
                "        \"TRADE_DT\": \"20230210\",\n" +
                "        \"PRODCD\": \"B034\",\n" +
                "        \"PRODNM\": \"고급휘발유\",\n" +
                "        \"PRICE\": \"1853.17\",\n" +
                "        \"DIFF\": \"-0.19\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"TRADE_DT\": \"20230210\",\n" +
                "        \"PRODCD\": \"D047\",\n" +
                "        \"PRODNM\": \"자동차용경유\",\n" +
                "        \"PRICE\": \"1620.22\",\n" +
                "        \"DIFF\": \"-3.59\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"TRADE_DT\": \"20230210\",\n" +
                "        \"PRODCD\": \"B027\",\n" +
                "        \"PRODNM\": \"휘발유\",\n" +
                "        \"PRICE\": \"1576.84\",\n" +
                "        \"DIFF\": \"-0.22\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"TRADE_DT\": \"20230210\",\n" +
                "        \"PRODCD\": \"C004\",\n" +
                "        \"PRODNM\": \"실내등유\",\n" +
                "        \"PRICE\": \"1473.59\",\n" +
                "        \"DIFF\": \"-1.50\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"TRADE_DT\": \"20230210\",\n" +
                "        \"PRODCD\": \"K015\",\n" +
                "        \"PRODNM\": \"자동차용부탄\",\n" +
                "        \"PRICE\": \"992.08\",\n" +
                "        \"DIFF\": \"+0.10\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}");
        //when
        Map<GasType, Long> nowAverageGasPrice = OpinentParser.parseNowAverage(averageJson);
        //then
        Arrays.stream(GasType.values())
                .forEach(gasType -> Assertions.assertTrue(nowAverageGasPrice.containsKey(gasType)));
    }

}