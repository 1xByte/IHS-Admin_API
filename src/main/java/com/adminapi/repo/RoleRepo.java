package com.adminapi.repo;

import com.adminapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo  extends JpaRepository<Role,Integer> {
}
