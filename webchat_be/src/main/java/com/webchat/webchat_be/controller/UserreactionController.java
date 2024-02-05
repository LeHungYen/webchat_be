package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserreactionDTO;
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
import com.webchat.webchat_be.service.UserreactionService;
import com.webchat.webchat_be.vo.UserreactionQueryVO;
import com.webchat.webchat_be.vo.UserreactionUpdateVO;
import com.webchat.webchat_be.vo.UserreactionVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userreaction")
public class UserreactionController {

    @Autowired
    private UserreactionService userreactionService;

    @PostMapping
    public String save(@Valid @RequestBody UserreactionVO vO) {
        return userreactionService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userreactionService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserreactionUpdateVO vO) {
        userreactionService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserreactionDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userreactionService.getById(id);
    }

    @GetMapping
    public Page<UserreactionDTO> query(@Valid UserreactionQueryVO vO) {
        return userreactionService.query(vO);
    }
}
