package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.GroupmemberDTO;
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
import com.webchat.webchat_be.service.GroupmemberService;
import com.webchat.webchat_be.vo.GroupmemberQueryVO;
import com.webchat.webchat_be.vo.GroupmemberUpdateVO;
import com.webchat.webchat_be.vo.GroupmemberVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/groupmember")
public class GroupmemberController {

    @Autowired
    private GroupmemberService groupmemberService;

    @PostMapping
    public String save(@Valid @RequestBody GroupmemberVO vO) {
        return groupmemberService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        groupmemberService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody GroupmemberUpdateVO vO) {
        groupmemberService.update(id, vO);
    }

    @GetMapping("/{id}")
    public GroupmemberDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return groupmemberService.getById(id);
    }

    @GetMapping
    public Page<GroupmemberDTO> query(@Valid GroupmemberQueryVO vO) {
        return groupmemberService.query(vO);
    }
}
