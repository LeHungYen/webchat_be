package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserfeedbackDTO;
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
import com.webchat.webchat_be.service.UserfeedbackService;
import com.webchat.webchat_be.vo.UserfeedbackQueryVO;
import com.webchat.webchat_be.vo.UserfeedbackUpdateVO;
import com.webchat.webchat_be.vo.UserfeedbackVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/userfeedback")
public class UserfeedbackController {

    @Autowired
    private UserfeedbackService userfeedbackService;

    @PostMapping
    public String save(@Valid @RequestBody UserfeedbackVO vO) {
        return userfeedbackService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userfeedbackService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserfeedbackUpdateVO vO) {
        userfeedbackService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserfeedbackDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userfeedbackService.getById(id);
    }

    @GetMapping
    public Page<UserfeedbackDTO> query(@Valid UserfeedbackQueryVO vO) {
        return userfeedbackService.query(vO);
    }
}
