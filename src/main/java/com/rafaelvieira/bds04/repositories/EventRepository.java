package com.rafaelvieira.bds04.repositories;

import com.rafaelvieira.bds04.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
