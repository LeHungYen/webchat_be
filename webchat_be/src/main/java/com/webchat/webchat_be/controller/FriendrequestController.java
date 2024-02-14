package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.FriendrequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.webchat.webchat_be.service.FriendrequestService;
import com.webchat.webchat_be.vo.FriendrequestQueryVO;
import com.webchat.webchat_be.vo.FriendrequestUpdateVO;
import com.webchat.webchat_be.vo.FriendrequestVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Validated
@RestController
@RequestMapping("/friendrequest")
@CrossOrigin
public class FriendrequestController {

    @Autowired
    private FriendrequestService friendrequestService;

    @CrossOrigin
    @PostMapping
    public String save(@Valid @RequestBody FriendrequestVO vO) {
        return friendrequestService.save(vO).toString();
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        friendrequestService.delete(id);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody FriendrequestUpdateVO vO) {
        friendrequestService.update(id, vO);
    }

    @GetMapping("/{id}")
    public FriendrequestDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return friendrequestService.getById(id);
    }

    @GetMapping
    public Page<FriendrequestDTO> query(@Valid FriendrequestQueryVO vO) {
        return friendrequestService.query(vO);
    }

    @CrossOrigin
    @GetMapping("/findBySenderIdAndReceiverId/")
    public ResponseEntity<?> findBySenderIdAndReceiverId(
            @RequestParam("senderId") int senderId,
            @RequestParam("receiverId") int receiverId
            ){
    FriendrequestDTO friendrequestDTO = friendrequestService.getBySenderIdAndReceiveId(senderId ,receiverId);

        if(friendrequestDTO != null){
            return ResponseEntity.ok(friendrequestDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Friend request not found");
        }
    }
}
