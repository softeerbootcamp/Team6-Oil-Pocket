package com.kaspi.backend.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class GasTypeTest {

    @Test
    @DisplayName("오피넷 유종 key값으로 부터 GasType 객체 받아오기-LPG")
    void getTypeFromOpinetData_LPG() {
        //given
        String opinetGasTypeLPG = GasType.LPG.getOpinetGasType();
        //when
        GasType gasType = GasType.getTypeFromOpinetData(opinetGasTypeLPG).get();
        //then
        assertThat(gasType).isEqualTo(GasType.LPG);
    }

    @Test
    @DisplayName("오피넷 유종 key값으로 부터 GasType 객체 받아오기-GASOLINE")
    void getTypeFromOpinetData_GASOLINE() {
        //given
        String opinetGasTypeLPG = GasType.GASOLINE.getOpinetGasType();
        //when
        GasType gasType = GasType.getTypeFromOpinetData(opinetGasTypeLPG).get();
        //then
        assertThat(gasType).isEqualTo(GasType.GASOLINE);
    }

    @Test
    @DisplayName("오피넷 유종 key값으로 부터 GasType 객체 받아오기-PREMIUM_GASOLINE")
    void getTypeFromOpinetData_PREMIUM_GASOLINE() {
        //given
        String opinetGasTypeLPG = GasType.PREMIUM_GASOLINE.getOpinetGasType();
        //when
        GasType gasType = GasType.getTypeFromOpinetData(opinetGasTypeLPG).get();
        //then
        assertThat(gasType).isEqualTo(GasType.PREMIUM_GASOLINE);
    }

    @Test
    @DisplayName("오피넷 유종 key값으로 부터 GasType 객체 받아오기-DIESEL")
    void getTypeFromOpinetData_DIESEL() {
        //given
        String opinetGasTypeLPG = GasType.DIESEL.getOpinetGasType();
        //when
        GasType gasType = GasType.getTypeFromOpinetData(opinetGasTypeLPG).get();
        //then
        assertThat(gasType).isEqualTo(GasType.DIESEL);
    }
}