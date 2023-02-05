package com.kaspi.backend.controller;

import com.kaspi.backend.domain.GasDetailDto;
import com.kaspi.backend.domain.GasStationDto;
import com.kaspi.backend.enums.GasType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gasStation")
public class GasStationController {
    @GetMapping
    public ResponseEntity<GasStationDto> getGasInfos() {
        GasDetailDto gasDetailDto = new GasDetailDto(GasType.GASOLINE, 2000, LocalDate.now());
        List<GasDetailDto> list = new ArrayList<>();
        list.add(gasDetailDto);
        GasStationDto gasStationDto = new GasStationDto( "서울 종로구", "㈜지에스이앤알 평창주유소",
                "서울 종로구 평창문화로 135 (평창동)", "현대오일뱅크", true, list);
        return new ResponseEntity<>(gasStationDto, HttpStatus.OK);
    }
}
