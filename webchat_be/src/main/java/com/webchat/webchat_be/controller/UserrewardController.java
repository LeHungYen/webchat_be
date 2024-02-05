package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserrewardDTO;
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
import com.webchat.webchat_be.service.UserrewardService;
import com.webchat.webchat_be.vo.UserrewardQueryVO;
import com.webchat.webchat_be.vo.UserrewardUpdateVO;
import com.webchat.webchat_be.vo.UserrewardVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userreward")
public class UserrewardController {

    @Autowired
    private UserrewardService userrewardService;

    @PostMapping
    public String save(@Valid @RequestBody UserrewardVO vO) {
        return userrewardService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userrewardService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserrewardUpdateVO vO) {
        userrewardService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserrewardDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userrewardService.getById(id);
    }

    @GetMapping
    public Page<UserrewardDTO> query(@Valid UserrewardQueryVO vO) {
        return userrewardService.query(vO);
    }
}
