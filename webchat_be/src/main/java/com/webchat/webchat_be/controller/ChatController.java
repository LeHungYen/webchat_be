package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.ChatDTO;
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
import com.webchat.webchat_be.service.ChatService;
import com.webchat.webchat_be.vo.ChatQueryVO;
import com.webchat.webchat_be.vo.ChatUpdateVO;
import com.webchat.webchat_be.vo.ChatVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public String save(@Valid @RequestBody ChatVO vO) {
        return chatService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        chatService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ChatUpdateVO vO) {
        chatService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ChatDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return chatService.getById(id);
    }

    @GetMapping
    public Page<ChatDTO> query(@Valid ChatQueryVO vO) {
        return chatService.query(vO);
    }
}
