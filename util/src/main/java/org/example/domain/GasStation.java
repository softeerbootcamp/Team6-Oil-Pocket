package org.example.domain;

import lombok.Getter;
import org.example.enums.AttributeIndex;
import org.example.enums.SchedulerIndex;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;

@Getter
@Table("gas_station")
public class GasStation {
    @Id
    private Long stationNo;
    private String area;
    private String name;
    private String address;
    private String brand;
    @Column("is_self")
    private boolean self;

    public GasStation(String area, String name, String address, String brand, boolean self) {
        this.area = area;
        this.name = name;
        this.address = address;
        this.brand = brand;
        this.self = self;
    }

    public static GasStation parseGasStation(String[] attribute) {
        return new GasStation(attribute[AttributeIndex.AREA.getIndex()],
                attribute[AttributeIndex.NAME.getIndex()],
                attribute[AttributeIndex.ADDRESS.getIndex()],
                attribute[AttributeIndex.BRAND.getIndex()],
                isSelf(attribute[AttributeIndex.SELF.getIndex()]));
    }

    public static GasStation parseNowGasStation(String[] attribute) {
        return new GasStation(attribute[SchedulerIndex.AREA.getIndex()],
                attribute[SchedulerIndex.NAME.getIndex()],
                attribute[SchedulerIndex.ADDRESS.getIndex()],
                attribute[SchedulerIndex.BRAND.getIndex()],
                isSelf(attribute[SchedulerIndex.SELF.getIndex()]));
    }

    private static boolean isSelf(String attribute) {
        if (attribute.equals("셀프")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GasStation that = (GasStation) o;
        return Objects.equals(address, that.address) && Objects.equals(brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, brand);
    }
}

