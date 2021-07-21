package com.devsuperior.bds04.services;


import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import com.devsuperior.bds04.services.handlers.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepo;

    @Transactional(readOnly = true)
    public List<EventDTO> findAll(){
        List<Event> list = repository.findAll(Sort.by("name"));
        return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
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
