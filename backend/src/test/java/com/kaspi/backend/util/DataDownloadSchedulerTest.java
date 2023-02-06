package com.kaspi.backend.util;

import com.kaspi.backend.enums.GasType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataDownloadSchedulerTest {
    @Test
    @DisplayName("스트링이 double 타입일 때 integer.valueOf() 메소드 확인")
    void StringToInteger() {
        String str = "1234.11";
        Assertions.assertThat(Integer.valueOf(str.substring(0, str.indexOf(".")))).isEqualTo(1234);
    }
}