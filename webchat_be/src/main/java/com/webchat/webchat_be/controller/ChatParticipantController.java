package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.ChatParticipantDTO;
import com.webchat.webchat_be.entity.ChatParticipant;
import com.webchat.webchat_be.service.ChatParticipantService;
import com.webchat.webchat_be.vo.ChatParticipantQueryVO;
import com.webchat.webchat_be.vo.ChatParticipantUpdateVO;
import com.webchat.webchat_be.vo.ChatParticipantVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/chatParticipant")
public class ChatParticipantController {

    @Autowired
    private ChatParticipantService chatParticipantService;

    @CrossOrigin
    @PostMapping
    public String save(@Valid @RequestBody ChatParticipantVO vO) {
        return chatParticipantService.save(vO).toString();
    }


    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        chatParticipantService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ChatParticipantUpdateVO vO) {
        chatParticipantService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ChatParticipantDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return chatParticipantService.getById(id);
    }

    @GetMapping("/getByUserId")
    public Page<ChatParticipantDTO> getByUserId(
            @RequestParam("userId") int userId,
            @RequestParam("page") int page ,
            @RequestParam("size") int size
    ) {
        return chatParticipantService.findByUserId(userId,page, size);
    }
}
