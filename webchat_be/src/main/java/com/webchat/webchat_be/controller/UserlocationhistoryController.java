package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserlocationhistoryDTO;
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
import com.webchat.webchat_be.service.UserlocationhistoryService;
import com.webchat.webchat_be.vo.UserlocationhistoryQueryVO;
import com.webchat.webchat_be.vo.UserlocationhistoryUpdateVO;
import com.webchat.webchat_be.vo.UserlocationhistoryVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userlocationhistory")
public class UserlocationhistoryController {

    @Autowired
    private UserlocationhistoryService userlocationhistoryService;

    @PostMapping
    public String save(@Valid @RequestBody UserlocationhistoryVO vO) {
        return userlocationhistoryService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userlocationhistoryService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserlocationhistoryUpdateVO vO) {
        userlocationhistoryService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserlocationhistoryDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userlocationhistoryService.getById(id);
    }

    @GetMapping
    public Page<UserlocationhistoryDTO> query(@Valid UserlocationhistoryQueryVO vO) {
        return userlocationhistoryService.query(vO);
    }
}
