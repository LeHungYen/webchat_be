package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.AdclickDTO;
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
import com.webchat.webchat_be.service.AdclickService;
import com.webchat.webchat_be.vo.AdclickQueryVO;
import com.webchat.webchat_be.vo.AdclickUpdateVO;
import com.webchat.webchat_be.vo.AdclickVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/adclick")
public class AdclickController {

    @Autowired
    private AdclickService adclickService;

    @PostMapping
    public String save(@Valid @RequestBody AdclickVO vO) {
        return adclickService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        adclickService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody AdclickUpdateVO vO) {
        adclickService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AdclickDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return adclickService.getById(id);
    }

    @GetMapping
    public Page<AdclickDTO> query(@Valid AdclickQueryVO vO) {
        return adclickService.query(vO);
    }
}
