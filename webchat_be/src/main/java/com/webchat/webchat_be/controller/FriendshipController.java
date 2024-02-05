package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.FriendshipDTO;
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
import com.webchat.webchat_be.service.FriendshipService;
import com.webchat.webchat_be.vo.FriendshipQueryVO;
import com.webchat.webchat_be.vo.FriendshipUpdateVO;
import com.webchat.webchat_be.vo.FriendshipVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/friendship")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @PostMapping
    public String save(@Valid @RequestBody FriendshipVO vO) {
        return friendshipService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        friendshipService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody FriendshipUpdateVO vO) {
        friendshipService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FriendshipDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return friendshipService.getById(id);
    }

    @GetMapping
    public Page<FriendshipDTO> query(@Valid FriendshipQueryVO vO) {
        return friendshipService.query(vO);
    }
}
