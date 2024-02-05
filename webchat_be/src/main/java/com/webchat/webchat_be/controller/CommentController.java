package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.CommentDTO;
import org.antlr.v4.runtime.misc.NotNull;
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
import com.webchat.webchat_be.service.CommentService;
import com.webchat.webchat_be.vo.CommentQueryVO;
import com.webchat.webchat_be.vo.CommentUpdateVO;
import com.webchat.webchat_be.vo.CommentVO;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public String save(@Valid @RequestBody CommentVO vO) {
        return commentService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        commentService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody CommentUpdateVO vO) {
        commentService.update(id, vO);
    }

    @GetMapping("/{id}")
    public CommentDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return commentService.getById(id);
    }

    @GetMapping
    public Page<CommentDTO> query(@Valid CommentQueryVO vO) {
        return commentService.query(vO);
    }
}
