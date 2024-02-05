package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserstatDTO;
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
import com.webchat.webchat_be.service.UserstatService;
import com.webchat.webchat_be.vo.UserstatQueryVO;
import com.webchat.webchat_be.vo.UserstatUpdateVO;
import com.webchat.webchat_be.vo.UserstatVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userstat")
public class UserstatController {

    @Autowired
    private UserstatService userstatService;

    @PostMapping
    public String save(@Valid @RequestBody UserstatVO vO) {
        return userstatService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userstatService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserstatUpdateVO vO) {
        userstatService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserstatDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userstatService.getById(id);
    }

    @GetMapping
    public Page<UserstatDTO> query(@Valid UserstatQueryVO vO) {
        return userstatService.query(vO);
    }
}
