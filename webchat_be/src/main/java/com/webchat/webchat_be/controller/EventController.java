package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.EventDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webchat.webchat_be.service.EventService;
import com.webchat.webchat_be.vo.EventQueryVO;
import com.webchat.webchat_be.vo.EventUpdateVO;
import com.webchat.webchat_be.vo.EventVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public String save(@Valid @RequestBody EventVO vO) {
        return eventService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        eventService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody EventUpdateVO vO) {
        eventService.update(id, vO);
    }

    @GetMapping("/{id}")
    public EventDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return eventService.getById(id);
    }

    @GetMapping
    public Page<EventDTO> query(@Valid EventQueryVO vO) {
        return eventService.query(vO);
    }
}
