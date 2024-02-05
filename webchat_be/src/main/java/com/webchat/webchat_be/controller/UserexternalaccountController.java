package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserexternalaccountDTO;
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
import com.webchat.webchat_be.service.UserexternalaccountService;
import com.webchat.webchat_be.vo.UserexternalaccountQueryVO;
import com.webchat.webchat_be.vo.UserexternalaccountUpdateVO;
import com.webchat.webchat_be.vo.UserexternalaccountVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userexternalaccount")
public class UserexternalaccountController {

    @Autowired
    private UserexternalaccountService userexternalaccountService;

    @PostMapping
    public String save(@Valid @RequestBody UserexternalaccountVO vO) {
        return userexternalaccountService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userexternalaccountService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserexternalaccountUpdateVO vO) {
        userexternalaccountService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserexternalaccountDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userexternalaccountService.getById(id);
    }

    @GetMapping
    public Page<UserexternalaccountDTO> query(@Valid UserexternalaccountQueryVO vO) {
        return userexternalaccountService.query(vO);
    }
}
