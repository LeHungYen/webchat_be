package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UsersearchhistoryDTO;
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
import com.webchat.webchat_be.service.UsersearchhistoryService;
import com.webchat.webchat_be.vo.UsersearchhistoryQueryVO;
import com.webchat.webchat_be.vo.UsersearchhistoryUpdateVO;
import com.webchat.webchat_be.vo.UsersearchhistoryVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/usersearchhistory")
public class UsersearchhistoryController {

    @Autowired
    private UsersearchhistoryService usersearchhistoryService;

    @PostMapping
    public String save(@Valid @RequestBody UsersearchhistoryVO vO) {
        return usersearchhistoryService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        usersearchhistoryService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UsersearchhistoryUpdateVO vO) {
        usersearchhistoryService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UsersearchhistoryDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return usersearchhistoryService.getById(id);
    }

    @GetMapping
    public Page<UsersearchhistoryDTO> query(@Valid UsersearchhistoryQueryVO vO) {
        return usersearchhistoryService.query(vO);
    }
}
