package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserfollowingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.webchat.webchat_be.service.UserfollowingService;
import com.webchat.webchat_be.vo.UserfollowingQueryVO;
import com.webchat.webchat_be.vo.UserfollowingUpdateVO;
import com.webchat.webchat_be.vo.UserfollowingVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userfollowing")
public class UserfollowingController {

    @Autowired
    private UserfollowingService userfollowingService;

    @CrossOrigin
    @PostMapping
    public String save(@Valid @RequestBody UserfollowingVO vO) {
        return userfollowingService.save(vO).toString();
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userfollowingService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserfollowingUpdateVO vO) {
        userfollowingService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserfollowingDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userfollowingService.getById(id);
    }

    @GetMapping
    public Page<UserfollowingDTO> query(@Valid UserfollowingQueryVO vO) {
        return userfollowingService.query(vO);
    }

    @CrossOrigin
    @GetMapping("/findByUserIdAndFollowingUserId")
    public ResponseEntity<?> findByUserIdAndFollowingUserId(@RequestParam("userId") int userId , @RequestParam("followingUserId") int followingUserId){
        UserfollowingDTO userfollowingDTO = userfollowingService.findByUserIdAndFollowingUserId(userId , followingUserId);
        if(userfollowingDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserFollowing not found");
        }else{
            return ResponseEntity.ok(userfollowingDTO);
        }
    }
}
