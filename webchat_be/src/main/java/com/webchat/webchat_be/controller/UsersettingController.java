package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UsersettingDTO;
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
import com.webchat.webchat_be.service.UsersettingService;
import com.webchat.webchat_be.vo.UsersettingQueryVO;
import com.webchat.webchat_be.vo.UsersettingUpdateVO;
import com.webchat.webchat_be.vo.UsersettingVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/usersetting")
public class UsersettingController {

    @Autowired
    private UsersettingService usersettingService;

    @PostMapping
    public String save(@Valid @RequestBody UsersettingVO vO) {
        return usersettingService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        usersettingService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UsersettingUpdateVO vO) {
        usersettingService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UsersettingDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return usersettingService.getById(id);
    }

    @GetMapping
    public Page<UsersettingDTO> query(@Valid UsersettingQueryVO vO) {
        return usersettingService.query(vO);
    }
}
