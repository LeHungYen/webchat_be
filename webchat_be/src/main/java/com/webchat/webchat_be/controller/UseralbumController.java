package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UseralbumDTO;
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
import com.webchat.webchat_be.service.UseralbumService;
import com.webchat.webchat_be.vo.UseralbumQueryVO;
import com.webchat.webchat_be.vo.UseralbumUpdateVO;
import com.webchat.webchat_be.vo.UseralbumVO;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/useralbum")
public class UseralbumController {

    @Autowired
    private UseralbumService useralbumService;

    @PostMapping
    public String save(@Valid @RequestBody UseralbumVO vO) {
        return useralbumService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        useralbumService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UseralbumUpdateVO vO) {
        useralbumService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UseralbumDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return useralbumService.getById(id);
    }

    @GetMapping
    public Page<UseralbumDTO> query(@Valid UseralbumQueryVO vO) {
        return useralbumService.query(vO);
    }
}
