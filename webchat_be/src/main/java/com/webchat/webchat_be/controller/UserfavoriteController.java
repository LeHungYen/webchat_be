package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserfavoriteDTO;
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
import com.webchat.webchat_be.service.UserfavoriteService;
import com.webchat.webchat_be.vo.UserfavoriteQueryVO;
import com.webchat.webchat_be.vo.UserfavoriteUpdateVO;
import com.webchat.webchat_be.vo.UserfavoriteVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userfavorite")
public class UserfavoriteController {

    @Autowired
    private UserfavoriteService userfavoriteService;

    @PostMapping
    public String save(@Valid @RequestBody UserfavoriteVO vO) {
        return userfavoriteService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userfavoriteService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserfavoriteUpdateVO vO) {
        userfavoriteService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserfavoriteDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userfavoriteService.getById(id);
    }

    @GetMapping
    public Page<UserfavoriteDTO> query(@Valid UserfavoriteQueryVO vO) {
        return userfavoriteService.query(vO);
    }
}
