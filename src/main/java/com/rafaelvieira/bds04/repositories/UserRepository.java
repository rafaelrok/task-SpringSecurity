package com.rafaelvieira.bds04.repositories;

import com.rafaelvieira.bds04.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);
}
