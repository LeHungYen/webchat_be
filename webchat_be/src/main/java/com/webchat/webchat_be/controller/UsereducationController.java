package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UsereducationDTO;
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
import com.webchat.webchat_be.service.UsereducationService;
import com.webchat.webchat_be.vo.UsereducationQueryVO;
import com.webchat.webchat_be.vo.UsereducationUpdateVO;
import com.webchat.webchat_be.vo.UsereducationVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/usereducation")
public class UsereducationController {

    @Autowired
    private UsereducationService usereducationService;

    @PostMapping
    public String save(@Valid @RequestBody UsereducationVO vO) {
        return usereducationService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        usereducationService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UsereducationUpdateVO vO) {
        usereducationService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UsereducationDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return usereducationService.getById(id);
    }

    @GetMapping
    public Page<UsereducationDTO> query(@Valid UsereducationQueryVO vO) {
        return usereducationService.query(vO);
    }
}
