package com.kaspi.backend.test;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "test")
public class TestEntity {
    @Id
    private Long test;

    private String name;

    public TestEntity(String name) {
        this.name = name;
    }

    public TestEntity() {
    }
}
