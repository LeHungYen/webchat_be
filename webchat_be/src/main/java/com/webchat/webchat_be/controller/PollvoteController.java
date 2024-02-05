package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.PollvoteDTO;
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
import com.webchat.webchat_be.service.PollvoteService;
import com.webchat.webchat_be.vo.PollvoteQueryVO;
import com.webchat.webchat_be.vo.PollvoteUpdateVO;
import com.webchat.webchat_be.vo.PollvoteVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/pollvote")
public class PollvoteController {

    @Autowired
    private PollvoteService pollvoteService;

    @PostMapping
    public String save(@Valid @RequestBody PollvoteVO vO) {
        return pollvoteService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        pollvoteService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody PollvoteUpdateVO vO) {
        pollvoteService.update(id, vO);
    }

    @GetMapping("/{id}")
    public PollvoteDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return pollvoteService.getById(id);
    }

    @GetMapping
    public Page<PollvoteDTO> query(@Valid PollvoteQueryVO vO) {
        return pollvoteService.query(vO);
    }
}
