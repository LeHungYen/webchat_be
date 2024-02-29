package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserDTO;
import com.webchat.webchat_be.dto.search.SearchUser;
import com.webchat.webchat_be.utilities.Utilities;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.webchat.webchat_be.service.UserService;
import com.webchat.webchat_be.vo.UserQueryVO;
import com.webchat.webchat_be.vo.UserUpdateVO;
import com.webchat.webchat_be.vo.UserVO;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @GetMapping("/getUserInfor")
    public ResponseEntity<?> getUserInfor () {
        UserDTO userDTO = Utilities.getUserDTOFromContext();
        return ResponseEntity.ok(userDTO);
    }

    @CrossOrigin
    @GetMapping("/searchUserByKey/")
    public ResponseEntity<List<SearchUser>> searchUserByKey(@RequestParam("keySearch") String keySearch) {
        List<SearchUser> users = userService.searchUserByKey(keySearch);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public String save(@Valid @RequestBody UserVO vO) {
        return userService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserUpdateVO vO) {
        userService.update(id, vO);
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public UserDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userService.getById(id);
    }

    @CrossOrigin
    @PutMapping("/updateLastChatId/{userId}/{chatId}")
    public UserDTO updateLastChatId( @NotNull @PathVariable("userId") int userId , @NotNull @PathVariable("chatId") int chatId) {
         return userService.updateLastChatId(userId , chatId );
    }
    @GetMapping
    public Page<UserDTO> query(@Valid UserQueryVO vO) {
        return userService.query(vO);
    }

    @MessageMapping("/connect")
//    @SendTo("/topic/connect")
    public void connect(@Payload int userId) {
        System.out.println(userId + " connected");
      userService.updateStatusOnline(userId);
    }

    @MessageMapping("/disconnect")
//    @SendTo("/topic/disconnect")
    public void disconnect(@Payload int userId) {
        System.out.println(userId + " disconnected");
        userService.updateStatusOffline(userId);
    }

}
