package com.kaspi.backend.util.opinet;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpinetRequestTest {

    @Test
    @DisplayName("오피넷 전국 유가 평균데이터 200ok 테스트")
    void requestNowAverage() {
        //given
        //when
        //then
        Assertions.assertDoesNotThrow(()->OpinetRequest.requestNowAverage());
    }


}