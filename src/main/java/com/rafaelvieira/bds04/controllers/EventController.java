package com.rafaelvieira.bds04.controllers;


import com.rafaelvieira.bds04.dto.EventDTO;
import com.rafaelvieira.bds04.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
        Page<EventDTO> page = service.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EventDTO> findById(@Valid @PathVariable Long id) {
        EventDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<EventDTO> insert(@Valid @RequestBody EventDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> update(@Valid @PathVariable Long id, @RequestBody EventDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
