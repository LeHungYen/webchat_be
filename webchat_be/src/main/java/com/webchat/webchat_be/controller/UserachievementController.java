package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.UserachievementDTO;
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
import com.webchat.webchat_be.service.UserachievementService;
import com.webchat.webchat_be.vo.UserachievementQueryVO;
import com.webchat.webchat_be.vo.UserachievementUpdateVO;
import com.webchat.webchat_be.vo.UserachievementVO;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/userachievement")
public class UserachievementController {

    @Autowired
    private UserachievementService userachievementService;

    @PostMapping
    public String save(@Valid @RequestBody UserachievementVO vO) {
        return userachievementService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        userachievementService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody UserachievementUpdateVO vO) {
        userachievementService.update(id, vO);
    }

    @GetMapping("/{id}")
    public UserachievementDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return userachievementService.getById(id);
    }

    @GetMapping
    public Page<UserachievementDTO> query(@Valid UserachievementQueryVO vO) {
        return userachievementService.query(vO);
    }
}
