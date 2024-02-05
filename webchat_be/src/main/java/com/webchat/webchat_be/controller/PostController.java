package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.PostDTO;
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
import com.webchat.webchat_be.service.PostService;
import com.webchat.webchat_be.vo.PostQueryVO;
import com.webchat.webchat_be.vo.PostUpdateVO;
import com.webchat.webchat_be.vo.PostVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping
    public String save(@Valid @RequestBody PostVO vO) {
        return postService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody PostUpdateVO vO) {
        postService.update(id, vO);
    }

    @GetMapping("/{id}")
    public PostDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return postService.getById(id);
    }

    @GetMapping
    public Page<PostDTO> query(@Valid PostQueryVO vO) {
        return postService.query(vO);
    }
}
