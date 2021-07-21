package com.devsuperior.bds04.controllers;


import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<List<EventDTO>> findAll() {
        List<EventDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
