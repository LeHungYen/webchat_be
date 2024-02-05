package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserskillDTO;
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
import com.webchat.webchat_be.service.UserskillService;
import com.webchat.webchat_be.vo.UserskillQueryVO;
import com.webchat.webchat_be.vo.UserskillUpdateVO;
import com.webchat.webchat_be.vo.UserskillVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userskill")
public class UserskillController {

    @Autowired
    private UserskillService userskillService;

    @PostMapping
    public String save(@Valid @RequestBody UserskillVO vO) {
        return userskillService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userskillService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserskillUpdateVO vO) {
        userskillService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserskillDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userskillService.getById(id);
    }

    @GetMapping
    public Page<UserskillDTO> query(@Valid UserskillQueryVO vO) {
        return userskillService.query(vO);
    }
}
