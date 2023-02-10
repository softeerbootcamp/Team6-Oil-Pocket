package com.kaspi.backend.domain;

import com.kaspi.backend.enums.GasType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(value = "user_gas_record")
public class UserGasRecord {


    @Id
    private Long UserGasRecordNo;
    @Column("user_no")
    private Long userNo;
    @Column("gas_station_no")
    private Long gasStationNo;
    private Date chargeDate;
    private Long refuelingPrice;
    private Long savingAmount;
    private GasType recordGasType;
    private Long recordGasAmount;


}
