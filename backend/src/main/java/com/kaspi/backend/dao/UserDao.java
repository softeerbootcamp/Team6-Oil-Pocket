package com.kaspi.backend.dao;

import com.kaspi.backend.domain.User;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    @Query("SELECT * FROM users WHERE id= :user_id")
    Optional<User> findByUserId(@Param("user_id") String userId);

    @Query("select user_no from users where gender = :gender and age = :age")
    Optional<List<Long>> findUserNoByGenderAndAge(@Param("gender") Gender gender, @Param("age") Age age);

}
