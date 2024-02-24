package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.NotificationDTO;
import com.webchat.webchat_be.dto.UserDTO;
import com.webchat.webchat_be.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.webchat.webchat_be.service.NotificationService;
import com.webchat.webchat_be.vo.NotificationQueryVO;
import com.webchat.webchat_be.vo.NotificationUpdateVO;
import com.webchat.webchat_be.vo.NotificationVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public String save(@Valid @RequestBody NotificationVO vO) {
        return notificationService.save(vO).toString();
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        notificationService.delete(id);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody NotificationUpdateVO vO) {
        notificationService.update(id, vO);
    }

    @GetMapping("/{id}")
    public NotificationDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return notificationService.getById(id);
    }

    @GetMapping
    public Page<NotificationDTO> query(@Valid NotificationQueryVO vO) {
        return notificationService.query(vO);
    }


//    @MessageMapping("/notification")
//    @SendTo("/topic/notification")
//    public NotificationDTO sendNotification(@Payload NotificationVO vO){
//        NotificationDTO notificationDTO = notificationService.save(vO);
//        return notificationDTO;
//    }

    @CrossOrigin
    @GetMapping("/getNotifications")
    public ResponseEntity<?> getNotifications (){
        return ResponseEntity.ok(notificationService.getNotifications());
    }

}
