package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.ChatmessageDTO;
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
import com.webchat.webchat_be.service.ChatmessageService;
import com.webchat.webchat_be.vo.ChatmessageQueryVO;
import com.webchat.webchat_be.vo.ChatmessageUpdateVO;
import com.webchat.webchat_be.vo.ChatmessageVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/chatmessage")
public class ChatmessageController {

    @Autowired
    private ChatmessageService chatmessageService;

    @PostMapping
    public String save(@Valid @RequestBody ChatmessageVO vO) {
        return chatmessageService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        chatmessageService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ChatmessageUpdateVO vO) {
        chatmessageService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ChatmessageDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return chatmessageService.getById(id);
    }

    @GetMapping
    public Page<ChatmessageDTO> query(@Valid ChatmessageQueryVO vO) {
        return chatmessageService.query(vO);
    }
}
