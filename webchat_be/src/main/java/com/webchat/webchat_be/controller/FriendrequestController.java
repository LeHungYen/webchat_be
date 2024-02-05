package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.FriendrequestDTO;
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
import com.webchat.webchat_be.service.FriendrequestService;
import com.webchat.webchat_be.vo.FriendrequestQueryVO;
import com.webchat.webchat_be.vo.FriendrequestUpdateVO;
import com.webchat.webchat_be.vo.FriendrequestVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/friendrequest")
public class FriendrequestController {

    @Autowired
    private FriendrequestService friendrequestService;

    @PostMapping
    public String save(@Valid @RequestBody FriendrequestVO vO) {
        return friendrequestService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        friendrequestService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody FriendrequestUpdateVO vO) {
        friendrequestService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FriendrequestDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return friendrequestService.getById(id);
    }

    @GetMapping
    public Page<FriendrequestDTO> query(@Valid FriendrequestQueryVO vO) {
        return friendrequestService.query(vO);
    }
}
