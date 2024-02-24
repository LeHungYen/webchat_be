package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.ChatPageDTO;
import com.webchat.webchat_be.service.ChatPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chatPage")
public class ChatPageController {
    @Autowired
    private ChatPageService chatPageService;

    @CrossOrigin
    @GetMapping
    public Page<ChatPageDTO> getChatPage (@RequestParam("page") int page) {
        return chatPageService.getChatPage(page);
    }
}
