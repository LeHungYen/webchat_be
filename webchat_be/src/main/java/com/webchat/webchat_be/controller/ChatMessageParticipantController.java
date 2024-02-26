package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.ChatMessageParticipantDTO;
import com.webchat.webchat_be.service.ChatMessageParticipantService;
import com.webchat.webchat_be.vo.ChatMessageParticipantQueryVO;
import com.webchat.webchat_be.vo.ChatMessageParticipantUpdateVO;
import com.webchat.webchat_be.vo.ChatMessageParticipantVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/chatMessageParticipant")
public class ChatMessageParticipantController {

    @Autowired
    private ChatMessageParticipantService chatMessageParticipantService;

    @PostMapping
    public String save(@Valid @RequestBody ChatMessageParticipantVO vO) {
        return chatMessageParticipantService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        chatMessageParticipantService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ChatMessageParticipantUpdateVO vO) {
        chatMessageParticipantService.update(id, vO);
    }

//    @CrossOrigin
//    @PutMapping("/updateStatusWatched/{chatParticipantId}/{chatId}")
//    public void updateStatusWatched(@PathVariable("chatParticipantId") Integer chatParticipantId , @PathVariable("chatId") Integer chatId) {
//        chatMessageParticipantService.setStatusReceivedToWatched(chatParticipantId, chatId);
//    }

    @GetMapping("/{id}")
    public ChatMessageParticipantDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return chatMessageParticipantService.getById(id);
    }

    @GetMapping
    public Page<ChatMessageParticipantDTO> query(@Valid ChatMessageParticipantQueryVO vO) {
        return chatMessageParticipantService.query(vO);
    }
}
