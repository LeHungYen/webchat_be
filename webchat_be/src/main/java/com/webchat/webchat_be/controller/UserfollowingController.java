package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserfollowingDTO;
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
import com.webchat.webchat_be.service.UserfollowingService;
import com.webchat.webchat_be.vo.UserfollowingQueryVO;
import com.webchat.webchat_be.vo.UserfollowingUpdateVO;
import com.webchat.webchat_be.vo.UserfollowingVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userfollowing")
public class UserfollowingController {

    @Autowired
    private UserfollowingService userfollowingService;

    @PostMapping
    public String save(@Valid @RequestBody UserfollowingVO vO) {
        return userfollowingService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userfollowingService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserfollowingUpdateVO vO) {
        userfollowingService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserfollowingDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userfollowingService.getById(id);
    }

    @GetMapping
    public Page<UserfollowingDTO> query(@Valid UserfollowingQueryVO vO) {
        return userfollowingService.query(vO);
    }
}
