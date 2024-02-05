package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.GroupDTO;
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
import com.webchat.webchat_be.service.GroupService;
import com.webchat.webchat_be.vo.GroupQueryVO;
import com.webchat.webchat_be.vo.GroupUpdateVO;
import com.webchat.webchat_be.vo.GroupVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public String save(@Valid @RequestBody GroupVO vO) {
        return groupService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        groupService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody GroupUpdateVO vO) {
        groupService.update(id, vO);
    }

    @GetMapping("/{id}")
    public GroupDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return groupService.getById(id);
    }

    @GetMapping
    public Page<GroupDTO> query(@Valid GroupQueryVO vO) {
        return groupService.query(vO);
    }
}
