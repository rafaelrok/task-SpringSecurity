package com.rafaelvieira.bds04.services;


import com.rafaelvieira.bds04.dto.EventDTO;
import com.rafaelvieira.bds04.entities.City;
import com.rafaelvieira.bds04.entities.Event;
import com.rafaelvieira.bds04.repositories.CityRepository;
import com.rafaelvieira.bds04.repositories.EventRepository;
import com.rafaelvieira.bds04.services.handlers.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepo;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        Page<Event> page = repository.findAll(pageable);
        return page.map(x -> new EventDTO(x));
    }

    @Transactional(readOnly = true)
    public EventDTO findById(Long id) {
        Optional<Event> obj = repository.findById(id);
        Event entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
        return new EventDTO(entity);
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event entity = new Event();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new EventDTO(entity);
    }

    @Transactional
    public EventDTO update(Long id, EventDTO dto){
        try {
            Event entity =  repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EventDTO(entity);

        }catch(EntityNotFoundException error){
            throw new ResourceNotFoundException("city doesn't exist " + id);
        }
    }

    private void copyDtoToEntity(EventDTO dto, Event entity) {

        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());
        entity.setCity(new City(dto.getCityId(), null));
    }

}
