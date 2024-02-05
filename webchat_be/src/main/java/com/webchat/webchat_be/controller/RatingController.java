package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.RatingDTO;
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
import com.webchat.webchat_be.service.RatingService;
import com.webchat.webchat_be.vo.RatingQueryVO;
import com.webchat.webchat_be.vo.RatingUpdateVO;
import com.webchat.webchat_be.vo.RatingVO;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public String save(@Valid @RequestBody RatingVO vO) {
        return ratingService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        ratingService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody RatingUpdateVO vO) {
        ratingService.update(id, vO);
    }

    @GetMapping("/{id}")
    public RatingDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return ratingService.getById(id);
    }

    @GetMapping
    public Page<RatingDTO> query(@Valid RatingQueryVO vO) {
        return ratingService.query(vO);
    }
}
