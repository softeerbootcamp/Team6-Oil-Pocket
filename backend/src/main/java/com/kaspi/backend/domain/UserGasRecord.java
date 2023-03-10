package com.kaspi.backend.domain;

import com.kaspi.backend.enums.GasType;
import java.time.LocalDate;
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
@Table(value = "user_gas_record")
public class UserGasRecord {


    @Id
    private Long userGasRecordNo;
    @Column("user_no")
    private Long userNo;
    @Column("gas_station_no")
    private Long gasStationNo;
    private LocalDate chargeDate;
    private Long refuelingPrice;
    private Long savingPrice;
    private GasType recordGasType;
    private Long recordGasAmount;


}
