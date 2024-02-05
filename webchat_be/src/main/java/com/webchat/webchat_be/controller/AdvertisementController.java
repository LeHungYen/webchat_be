package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.AdvertisementDTO;
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
import com.webchat.webchat_be.service.AdvertisementService;
import com.webchat.webchat_be.vo.AdvertisementQueryVO;
import com.webchat.webchat_be.vo.AdvertisementUpdateVO;
import com.webchat.webchat_be.vo.AdvertisementVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/advertisement")
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping
    public String save(@Valid @RequestBody AdvertisementVO vO) {
        return advertisementService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        advertisementService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody AdvertisementUpdateVO vO) {
        advertisementService.update(id, vO);
    }

    @GetMapping("/{id}")
    public AdvertisementDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return advertisementService.getById(id);
    }

    @GetMapping
    public Page<AdvertisementDTO> query(@Valid AdvertisementQueryVO vO) {
        return advertisementService.query(vO);
    }
}
