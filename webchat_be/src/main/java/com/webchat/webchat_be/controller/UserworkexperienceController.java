package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserworkexperienceDTO;
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
import com.webchat.webchat_be.service.UserworkexperienceService;
import com.webchat.webchat_be.vo.UserworkexperienceQueryVO;
import com.webchat.webchat_be.vo.UserworkexperienceUpdateVO;
import com.webchat.webchat_be.vo.UserworkexperienceVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userworkexperience")
public class UserworkexperienceController {

    @Autowired
    private UserworkexperienceService userworkexperienceService;

    @PostMapping
    public String save(@Valid @RequestBody UserworkexperienceVO vO) {
        return userworkexperienceService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userworkexperienceService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserworkexperienceUpdateVO vO) {
        userworkexperienceService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserworkexperienceDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userworkexperienceService.getById(id);
    }

    @GetMapping
    public Page<UserworkexperienceDTO> query(@Valid UserworkexperienceQueryVO vO) {
        return userworkexperienceService.query(vO);
    }
}
