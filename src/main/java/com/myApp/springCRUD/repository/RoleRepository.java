package com.myApp.springCRUD.repository;

import com.myApp.springCRUD.model.EnumRole;
import com.myApp.springCRUD.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole name);
}
