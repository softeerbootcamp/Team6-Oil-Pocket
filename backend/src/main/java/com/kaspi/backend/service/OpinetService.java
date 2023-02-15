package com.kaspi.backend.service;

import com.kaspi.backend.enums.GasType;
import com.kaspi.backend.util.exception.OpinetException;
import com.kaspi.backend.util.opinet.OpinentParser;
import com.kaspi.backend.util.opinet.OpinetRequest;
import com.kaspi.backend.util.response.code.ErrorCode;
import java.io.IOException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OpinetService {

    public Long nationalAvgOilPrice(GasType gasType) {
        Long matchNationalGasPrice;
        try {
            JSONObject jsonAverageGasPrice = OpinetRequest.requestNowAverage();
            Map<GasType, Long> nationwideGasPricesAvg = OpinentParser.parseNowAverage(jsonAverageGasPrice);
            matchNationalGasPrice = nationwideGasPricesAvg.get(gasType);
            log.info("금일 전국 유가 평균 API요청(오피넷)");
            log.info("금율 유가:{}, 가스타입:{}", matchNationalGasPrice, gasType.name());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            log.error("오피넷-전국 유가 평균 데이터 요청 및 파싱시에 에러");
            throw new OpinetException(ErrorCode.OPINET_EXCEPTION);
        }
        return matchNationalGasPrice;
    }
}
