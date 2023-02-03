package com.kaspi.backend.dao;

import com.kaspi.backend.domain.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    @Query(value = "INSERT INTO users (id, password, gender, age) VALUES (:id, :password, :gender, :age)")
    @Modifying
    Long insertUser(@Param("id") String id, @Param("password") String password, @Param("gender") String gender, @Param("age") String age);


    @Query("select * from users where user_no= :userNo")
    Optional<User> findByUserNo(@Param("userNo") Long userNo);

}
