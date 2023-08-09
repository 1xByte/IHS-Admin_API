package com.adminapi.repo;

import com.adminapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,String> {
    Optional findByUserId(Integer employeeId);

    @Query("SELECT p FROM User p WHERE p.userName LIKE %:search% or p.userEmail LIKE %:search%")
    User getBySearch(String search);

    Optional<Object> findByUserEmail(String adminEmail);
}
