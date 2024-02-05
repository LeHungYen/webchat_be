package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.PollDTO;
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
import com.webchat.webchat_be.service.PollService;
import com.webchat.webchat_be.vo.PollQueryVO;
import com.webchat.webchat_be.vo.PollUpdateVO;
import com.webchat.webchat_be.vo.PollVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping
    public String save(@Valid @RequestBody PollVO vO) {
        return pollService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        pollService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody PollUpdateVO vO) {
        pollService.update(id, vO);
    }

    @GetMapping("/{id}")
    public PollDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return pollService.getById(id);
    }

    @GetMapping
    public Page<PollDTO> query(@Valid PollQueryVO vO) {
        return pollService.query(vO);
    }
}
