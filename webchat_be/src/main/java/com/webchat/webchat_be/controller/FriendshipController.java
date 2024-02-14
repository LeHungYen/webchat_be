package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.FriendshipDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @CrossOrigin
    @PostMapping
    public String save(@Valid @RequestBody FriendshipVO vO) {
        return friendshipService.save(vO).toString();
    }

    @CrossOrigin
    @DeleteMapping()
    public void delete(
            @RequestParam("friendshipId") int friendshipId,
            @RequestParam("userId1") int userId1,
            @RequestParam("userId2") int userId2)
    {
        friendshipService.delete(friendshipId , userId1 , userId2);
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

    @CrossOrigin
    @GetMapping("/findByUserId1AndUserId2")
    public ResponseEntity<?> findByUserId1AndUserId2(@RequestParam("userId1") int userId1 , @RequestParam("userId2") int userId2){
        FriendshipDTO friendshipDTO = friendshipService.findByUserId1AndUserId2(userId1 , userId2);
        if(friendshipDTO != null){
            return ResponseEntity.ok(friendshipDTO);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Friendship not found");
        }
    }
}
