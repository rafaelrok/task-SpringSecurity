package com.devsuperior.bds04.repositories;

import com.devsuperior.bds04.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
