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
import java.util.Objects;
import java.util.Set;

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
                GasDetail gasDetail = parseLpgGasDetail(stationAndDetailSet.getGasStation(), stationAndDetailSet.getAttribute(), stationAndDetailSet.getDate());
                if (list.contains(gasDetail)) continue;
                list.add(gasDetail);
                continue;
            }
            List<GasDetail> gasDetails = parseListGasDetail(stationAndDetailSet.getGasStation(), stationAndDetailSet.getAttribute(), stationAndDetailSet.getDate());
            if (list.containsAll(gasDetails)) continue;
            list.addAll(gasDetails);
        }
        return list;
    }

    public static String setToCsv(Set<GasDetail> gasDetailSet) {
        StringBuilder sb = new StringBuilder();
        for (GasDetail gasDetail : gasDetailSet) {
            sb.append(gasDetail.getStationNo() + "," + gasDetail.getGasType() + "," + gasDetail.getPrice() + "," + gasDetail.getDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GasDetail gasDetail = (GasDetail) o;
        return price == gasDetail.price && Objects.equals(stationNo, gasDetail.stationNo) && gasType == gasDetail.gasType && Objects.equals(date, gasDetail.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stationNo, price, gasType, date);
    }
}
