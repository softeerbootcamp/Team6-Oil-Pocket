package com.kaspi.backend.domain;

import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private Long userNo;
    private String id;
    private String password;

    @Column(value = "gender")
    private Gender gender;

    @Column(value = "age")
    private Age age;
}
