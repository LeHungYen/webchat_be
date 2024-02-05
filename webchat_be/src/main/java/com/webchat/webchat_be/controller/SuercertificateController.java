package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.SuercertificateDTO;
import org.jetbrains.annotations.NotNull;
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
import com.webchat.webchat_be.service.SuercertificateService;
import com.webchat.webchat_be.vo.SuercertificateQueryVO;
import com.webchat.webchat_be.vo.SuercertificateUpdateVO;
import com.webchat.webchat_be.vo.SuercertificateVO;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/suercertificate")
public class SuercertificateController {

    @Autowired
    private SuercertificateService suercertificateService;

    @PostMapping
    public String save(@Valid @RequestBody SuercertificateVO vO) {
        return suercertificateService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        suercertificateService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody SuercertificateUpdateVO vO) {
        suercertificateService.update(id, vO);
    }

    @GetMapping("/{id}")
    public SuercertificateDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return suercertificateService.getById(id);
    }

    @GetMapping
    public Page<SuercertificateDTO> query(@Valid SuercertificateQueryVO vO) {
        return suercertificateService.query(vO);
    }
}
