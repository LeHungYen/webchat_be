package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserprojectsDTO;
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
import com.webchat.webchat_be.service.UserprojectsService;
import com.webchat.webchat_be.vo.UserprojectsQueryVO;
import com.webchat.webchat_be.vo.UserprojectsUpdateVO;
import com.webchat.webchat_be.vo.UserprojectsVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userprojects")
public class UserprojectsController {

    @Autowired
    private UserprojectsService userprojectsService;

    @PostMapping
    public String save(@Valid @RequestBody UserprojectsVO vO) {
        return userprojectsService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userprojectsService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserprojectsUpdateVO vO) {
        userprojectsService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserprojectsDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userprojectsService.getById(id);
    }

    @GetMapping
    public Page<UserprojectsDTO> query(@Valid UserprojectsQueryVO vO) {
        return userprojectsService.query(vO);
    }
}
