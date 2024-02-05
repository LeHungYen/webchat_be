package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserphotoDTO;
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
import com.webchat.webchat_be.service.UserphotoService;
import com.webchat.webchat_be.vo.UserphotoQueryVO;
import com.webchat.webchat_be.vo.UserphotoUpdateVO;
import com.webchat.webchat_be.vo.UserphotoVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userphoto")
public class UserphotoController {

    @Autowired
    private UserphotoService userphotoService;

    @PostMapping
    public String save(@Valid @RequestBody UserphotoVO vO) {
        return userphotoService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userphotoService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserphotoUpdateVO vO) {
        userphotoService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserphotoDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userphotoService.getById(id);
    }

    @GetMapping
    public Page<UserphotoDTO> query(@Valid UserphotoQueryVO vO) {
        return userphotoService.query(vO);
    }
}
