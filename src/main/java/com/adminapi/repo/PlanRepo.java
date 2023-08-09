package com.adminapi.repo;

import com.adminapi.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlanRepo extends JpaRepository<Plan,String> {
    Optional<Plan> findByPlanId(Integer planId);


    @Query("SELECT p FROM Plan p WHERE p.planName LIKE %:search%")
    Plan getBySearch(String search);
}
