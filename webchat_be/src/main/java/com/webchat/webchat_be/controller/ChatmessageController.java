package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.ChatmessageDTO;
import com.webchat.webchat_be.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.webchat.webchat_be.service.ChatmessageService;
import com.webchat.webchat_be.vo.ChatmessageQueryVO;
import com.webchat.webchat_be.vo.ChatmessageUpdateVO;
import com.webchat.webchat_be.vo.ChatmessageVO;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Validated
@RestController
@RequestMapping("/chatmessage")
public class ChatmessageController {

    @Autowired
    private ChatmessageService chatmessageService;

    @CrossOrigin
    @PostMapping
    public ChatmessageDTO save(@Valid @RequestBody ChatmessageVO vO) {
        return chatmessageService.save(vO);
    }

    @CrossOrigin
    @PostMapping("/saveImg")
    public ChatmessageDTO saveImg(
            @RequestParam("chatId") int chatId ,
            @RequestParam("chatParticipantId") int chatParticipantId,
            @RequestParam("file") MultipartFile file) throws IOException {
        return chatmessageService.saveImg(chatId,chatParticipantId,file);
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

//    @MessageMapping("/chatMessage")
//    @SendTo("/topic/chatMessage")
//    public ChatmessageDTO sendChatMessage(@Payload ChatmessageVO vO){
//        ChatmessageDTO chatmessageDTO = chatmessageService.save(vO);
//        return chatmessageDTO;
//    }

    @CrossOrigin
    @GetMapping ("/findByChatId")
    public Page<ChatmessageDTO> findByChatId(@RequestParam("chatId") int chatId , @RequestParam("pageNumber") int pageNumber){
        return chatmessageService.findAllByChatId(chatId , pageNumber);
    }

    @GetMapping
    public Page<ChatmessageDTO> query(@Valid ChatmessageQueryVO vO) {
        return chatmessageService.query(vO);
    }
}
