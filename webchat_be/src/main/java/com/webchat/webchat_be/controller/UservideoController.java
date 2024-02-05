package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UservideoDTO;
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
import com.webchat.webchat_be.service.UservideoService;
import com.webchat.webchat_be.vo.UservideoQueryVO;
import com.webchat.webchat_be.vo.UservideoUpdateVO;
import com.webchat.webchat_be.vo.UservideoVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/uservideo")
public class UservideoController {

    @Autowired
    private UservideoService uservideoService;

    @PostMapping
    public String save(@Valid @RequestBody UservideoVO vO) {
        return uservideoService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        uservideoService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UservideoUpdateVO vO) {
        uservideoService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UservideoDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return uservideoService.getById(id);
    }

    @GetMapping
    public Page<UservideoDTO> query(@Valid UservideoQueryVO vO) {
        return uservideoService.query(vO);
    }
}
