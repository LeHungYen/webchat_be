package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserdevicetokenDTO;
import org.jetbrains.annotations.NotNull;
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
import com.webchat.webchat_be.service.UserdevicetokenService;
import com.webchat.webchat_be.vo.UserdevicetokenQueryVO;
import com.webchat.webchat_be.vo.UserdevicetokenUpdateVO;
import com.webchat.webchat_be.vo.UserdevicetokenVO;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/userdevicetoken")
public class UserdevicetokenController {

    @Autowired
    private UserdevicetokenService userdevicetokenService;

    @PostMapping
    public String save(@Valid @RequestBody UserdevicetokenVO vO) {
        return userdevicetokenService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userdevicetokenService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserdevicetokenUpdateVO vO) {
        userdevicetokenService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserdevicetokenDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userdevicetokenService.getById(id);
    }

    @GetMapping
    public Page<UserdevicetokenDTO> query(@Valid UserdevicetokenQueryVO vO) {
        return userdevicetokenService.query(vO);
    }
}
