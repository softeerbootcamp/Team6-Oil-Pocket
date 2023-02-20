package org.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.enums.AttributeIndex;
import org.example.enums.GasType;
import org.example.enums.SchedulerIndex;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Table("gas_detail")
public class GasDetail {

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

    public static List<GasDetail> parseListGasDetail(GasStation gasStation, String[] attribute) {
        List<GasDetail> list = new ArrayList<>();
        list.add(new GasDetail(gasStation, Integer.valueOf(attribute[AttributeIndex.PREMIUM_GASOLINE.getIndex()]), GasType.PREMIUM_GASOLINE, LocalDate.parse(attribute[AttributeIndex.DATE.getIndex()], DateTimeFormatter.ofPattern("yyyyMMdd"))));
        list.add(new GasDetail(gasStation, Integer.valueOf(attribute[AttributeIndex.GASOLINE.getIndex()]), GasType.GASOLINE, LocalDate.parse(attribute[AttributeIndex.DATE.getIndex()], DateTimeFormatter.ofPattern("yyyyMMdd"))));
        list.add(new GasDetail(gasStation, Integer.valueOf(attribute[AttributeIndex.DIESEL.getIndex()]), GasType.DIESEL, LocalDate.parse(attribute[AttributeIndex.DATE.getIndex()], DateTimeFormatter.ofPattern("yyyyMMdd"))));
        return list;
    }

    public static List<GasDetail> parseListGasDetail(GasStation gasStation, String[] attribute, LocalDate date) {
        List<GasDetail> list = new ArrayList<>();
        list.add(new GasDetail(gasStation, Integer.valueOf(attribute[SchedulerIndex.PREMIUM_GASOLINE.getIndex()]), GasType.PREMIUM_GASOLINE, date));
        list.add(new GasDetail(gasStation, Integer.valueOf(attribute[SchedulerIndex.GASOLINE.getIndex()]), GasType.GASOLINE, date));
        list.add(new GasDetail(gasStation, Integer.valueOf(attribute[SchedulerIndex.DIESEL.getIndex()]), GasType.DIESEL, date));
        return list;
    }

    public static GasDetail parseLpgGasDetail(GasStation gasStation, String[] attribute) {
        return new GasDetail(gasStation, Integer.valueOf(attribute[AttributeIndex.LPG.getIndex()]), GasType.LPG, LocalDate.parse(attribute[AttributeIndex.DATE.getIndex()], DateTimeFormatter.ofPattern("yyyyMMdd")));
    }

    public static GasDetail parseLpgGasDetail(GasStation gasStation, String[] attribute, LocalDate date) {
        return new GasDetail(gasStation, Integer.valueOf(attribute[SchedulerIndex.LPG.getIndex()]), GasType.LPG, date);
    }

    public static List<GasDetail> stationAndDetailSetToGasDetails(List<StationAndDetailSet> notYetList) {
        List<GasDetail> list = new ArrayList<>();
        for (StationAndDetailSet stationAndDetailSet : notYetList) {
            if (stationAndDetailSet.isLpg()) {
                list.add(parseLpgGasDetail(stationAndDetailSet.getGasStation(), stationAndDetailSet.getAttribute(), stationAndDetailSet.getDate()));
                continue;
            }
            list.addAll(parseListGasDetail(stationAndDetailSet.getGasStation(), stationAndDetailSet.getAttribute(), stationAndDetailSet.getDate()));
        }
        return list;
    }
}
