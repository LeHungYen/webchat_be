package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserlanguageDTO;
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
import com.webchat.webchat_be.service.UserlanguageService;
import com.webchat.webchat_be.vo.UserlanguageQueryVO;
import com.webchat.webchat_be.vo.UserlanguageUpdateVO;
import com.webchat.webchat_be.vo.UserlanguageVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userlanguage")
public class UserlanguageController {

    @Autowired
    private UserlanguageService userlanguageService;

    @PostMapping
    public String save(@Valid @RequestBody UserlanguageVO vO) {
        return userlanguageService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userlanguageService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserlanguageUpdateVO vO) {
        userlanguageService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserlanguageDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userlanguageService.getById(id);
    }

    @GetMapping
    public Page<UserlanguageDTO> query(@Valid UserlanguageQueryVO vO) {
        return userlanguageService.query(vO);
    }
}
