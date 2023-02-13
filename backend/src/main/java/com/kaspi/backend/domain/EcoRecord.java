package com.kaspi.backend.domain;

import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EcoRecord {
    private long userNo;
    private Gender gender;
    private Age age;
    private long saving_price;

    @Override
    public boolean equals(Object obj) {
        return this.userNo == ((EcoRecord)obj).userNo;
    }
}
