package com.kaspi.backend.domain;

import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "users")
public class User {

    @Id
    private Long userNo;
    private String id;
    private String password;

    @Column(value = "gender")
    private Gender gender;

    @Column(value = "age")
    private Age age;


    /**
     * 빌더 패턴 적용
     */
    private User(builder builder) {
        this.userNo = builder.userNo;
        this.id = builder.id;
        this.password = builder.password;
        this.gender = builder.gender;
        this.age = builder.age;
    }

    public User() {
    }

    public static class builder {
        private Long userNo;
        private String id;
        private String password;
        private Gender gender;
        private Age age;

        public builder userNo(Long userNo) {
            this.userNo = userNo;
            return this;
        }

        public builder id(String id) {
            this.id = id;
            return this;
        }

        public builder password(String password) {
            this.password = password;
            return this;
        }

        public builder gender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public builder age(Age age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


    public Long getUserNo() {
        return userNo;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public Age getAge() {
        return age;
    }
}
