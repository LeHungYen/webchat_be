package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        notificationService.delete(id);
    }

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
}
