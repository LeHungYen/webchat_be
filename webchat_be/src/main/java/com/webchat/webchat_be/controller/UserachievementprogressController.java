package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserachievementprogressDTO;
import org.jetbrains.annotations.NotNull;
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
import com.webchat.webchat_be.service.UserachievementprogressService;
import com.webchat.webchat_be.vo.UserachievementprogressQueryVO;
import com.webchat.webchat_be.vo.UserachievementprogressUpdateVO;
import com.webchat.webchat_be.vo.UserachievementprogressVO;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/userachievementprogress")
public class UserachievementprogressController {

    @Autowired
    private UserachievementprogressService userachievementprogressService;

    @PostMapping
    public String save(@Valid @RequestBody UserachievementprogressVO vO) {
        return userachievementprogressService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userachievementprogressService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserachievementprogressUpdateVO vO) {
        userachievementprogressService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserachievementprogressDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userachievementprogressService.getById(id);
    }

    @GetMapping
    public Page<UserachievementprogressDTO> query(@Valid UserachievementprogressQueryVO vO) {
        return userachievementprogressService.query(vO);
    }
}
