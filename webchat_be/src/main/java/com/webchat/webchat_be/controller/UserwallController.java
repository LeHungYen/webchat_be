package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserwallDTO;
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
import com.webchat.webchat_be.service.UserwallService;
import com.webchat.webchat_be.vo.UserwallQueryVO;
import com.webchat.webchat_be.vo.UserwallUpdateVO;
import com.webchat.webchat_be.vo.UserwallVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userwall")
public class UserwallController {

    @Autowired
    private UserwallService userwallService;

    @PostMapping
    public String save(@Valid @RequestBody UserwallVO vO) {
        return userwallService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userwallService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserwallUpdateVO vO) {
        userwallService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserwallDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userwallService.getById(id);
    }

    @GetMapping
    public Page<UserwallDTO> query(@Valid UserwallQueryVO vO) {
        return userwallService.query(vO);
    }
}
