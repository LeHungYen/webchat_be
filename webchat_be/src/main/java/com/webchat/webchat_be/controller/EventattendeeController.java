package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.EventattendeeDTO;
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
import com.webchat.webchat_be.service.EventattendeeService;
import com.webchat.webchat_be.vo.EventattendeeQueryVO;
import com.webchat.webchat_be.vo.EventattendeeUpdateVO;
import com.webchat.webchat_be.vo.EventattendeeVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/eventattendee")
public class EventattendeeController {

    @Autowired
    private EventattendeeService eventattendeeService;

    @PostMapping
    public String save(@Valid @RequestBody EventattendeeVO vO) {
        return eventattendeeService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        eventattendeeService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody EventattendeeUpdateVO vO) {
        eventattendeeService.update(id, vO);
    }

    @GetMapping("/{id}")
    public EventattendeeDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return eventattendeeService.getById(id);
    }

    @GetMapping
    public Page<EventattendeeDTO> query(@Valid EventattendeeQueryVO vO) {
        return eventattendeeService.query(vO);
    }
}
