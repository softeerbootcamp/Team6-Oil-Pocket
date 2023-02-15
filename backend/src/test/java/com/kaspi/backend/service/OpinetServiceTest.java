package com.kaspi.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import com.kaspi.backend.enums.GasType;
import java.io.IOException;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OpinetServiceTest {

    @InjectMocks
    private OpinetService opinetService;


    @Test
    @DisplayName("오피넷으로 부터 전국 유가 평균 데이터 가스타입에 맞게 받아오는 로직 예외 미발생 테스트")
    public void testNationalAvgOilPrice() throws JSONException, IOException {
        GasType gasType = GasType.GASOLINE;

        assertDoesNotThrow(() -> {
            opinetService.nationalAvgOilPrice(gasType);
        });
    }
}