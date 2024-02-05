package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserhobbieDTO;
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
import com.webchat.webchat_be.service.UserhobbieService;
import com.webchat.webchat_be.vo.UserhobbieQueryVO;
import com.webchat.webchat_be.vo.UserhobbieUpdateVO;
import com.webchat.webchat_be.vo.UserhobbieVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userhobbie")
public class UserhobbieController {

    @Autowired
    private UserhobbieService userhobbieService;

    @PostMapping
    public String save(@Valid @RequestBody UserhobbieVO vO) {
        return userhobbieService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userhobbieService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserhobbieUpdateVO vO) {
        userhobbieService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserhobbieDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userhobbieService.getById(id);
    }

    @GetMapping
    public Page<UserhobbieDTO> query(@Valid UserhobbieQueryVO vO) {
        return userhobbieService.query(vO);
    }
}
