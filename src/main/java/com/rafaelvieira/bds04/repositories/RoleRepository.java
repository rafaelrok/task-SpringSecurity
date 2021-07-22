package com.rafaelvieira.bds04.repositories;

import com.rafaelvieira.bds04.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
