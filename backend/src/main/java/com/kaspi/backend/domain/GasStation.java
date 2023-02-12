package com.kaspi.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
        return new GasStation(attribute[1], attribute[2], attribute[3], attribute[4], checkSelf(attribute[5]));
    }

    private static boolean checkSelf(String attribute) {
        if (attribute.equals("셀프")) {
            return true;
        }
        return false;
    }
}
