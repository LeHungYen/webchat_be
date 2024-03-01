package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.ChatDTO;
import com.webchat.webchat_be.dto.ChatmessageDTO;
import com.webchat.webchat_be.entity.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.webchat.webchat_be.service.ChatService;
import com.webchat.webchat_be.vo.ChatQueryVO;
import com.webchat.webchat_be.vo.ChatUpdateVO;
import com.webchat.webchat_be.vo.ChatVO;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Validated
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @CrossOrigin
    @PostMapping
    public String save(@Valid @RequestBody ChatVO vO) {
        return chatService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        chatService.delete(id);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ChatUpdateVO vO) {
        chatService.update(id, vO);
    }

    @CrossOrigin
    @PostMapping("/saveImg")
    public ChatmessageDTO saveImg(
            @RequestParam("chatId") int chatId ,
            @RequestParam("chatParticipantId") int chatParticipantId,
            @RequestParam("file") MultipartFile file) throws IOException {
        return chatService.saveImg(chatId , chatParticipantId,file);
    }

    @GetMapping("/{id}")
    public ChatDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return chatService.getById(id);
    }

    @GetMapping("/getByType/{userId}/{userId2}/{type}")
    public ChatDTO getByType(
            @PathVariable("userId") Integer userId,
            @PathVariable("userId2") Integer userId2,
            @PathVariable("type") String type

    ) {
        return chatService.getByType(userId , userId2 , type);
    }


    @GetMapping
    public Page<ChatDTO> query(@Valid ChatQueryVO vO) {
        return chatService.query(vO);
    }
}
