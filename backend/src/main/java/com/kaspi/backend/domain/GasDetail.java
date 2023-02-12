package com.kaspi.backend.domain;

import com.kaspi.backend.enums.GasType;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("gas_detail")
public class GasDetail {
    private static final int ADDRESS = 4;
    private static final int PREMIUM_GASOLINE = 6;
    private static final int GASOLINE = 7;
    private static final int DIESEL = 8;
    private static final int LPG = 6;
    @Id
    private Long detailNo;
    @Column("station_no")
    private Long stationNo;
    private int price;
    private GasType gasType;
    @Column("created_date")
    private LocalDate date;

    public GasDetail(GasStation gasStation, int price, GasType gasType, LocalDate date) {
        this.stationNo = gasStation.getStationNo();
        this.price = price;
        this.gasType = gasType;
        this.date = date;
    }
    
    public static List<GasDetail> parseListGasDetail(GasStation gasStation, String[] attribute, LocalDate date) {
        List<GasDetail> list = new ArrayList<>();
        list.add(new GasDetail(gasStation, Integer.valueOf(attribute[PREMIUM_GASOLINE]), GasType.PREMIUM_GASOLINE, date));
        list.add(new GasDetail(gasStation, Integer.valueOf(attribute[GASOLINE]), GasType.GASOLINE, date));
        list.add(new GasDetail(gasStation, Integer.valueOf(attribute[DIESEL]), GasType.DIESEL, date));
        return list;
    }

    public static GasDetail parseLpgGasDetail(GasStation gasStation, String[] attribute, LocalDate date) {
        return new GasDetail(gasStation, Integer.valueOf(attribute[LPG]), GasType.LPG, date);
    }

    public static String getNowDateToStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date());
        return currentDate;
    }
}
