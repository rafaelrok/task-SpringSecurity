package com.rafaelvieira.bds04.services;


import com.rafaelvieira.bds04.dto.CityDTO;
import com.rafaelvieira.bds04.entities.City;
import com.rafaelvieira.bds04.repositories.CityRepository;
import com.rafaelvieira.bds04.services.handlers.DataBaseException;
import com.rafaelvieira.bds04.services.handlers.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll(){
        List<City> list = repository.findAll(Sort.by("name"));
        return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City entity = new City();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CityDTO(entity);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Cidade não encontrada " + id);
        }
        catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }

    }
}
