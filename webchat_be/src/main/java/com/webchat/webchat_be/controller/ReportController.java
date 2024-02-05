package com.webchat.webchat_be.controller;

import com.webchat.webchat_be.dto.ReportDTO;
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
import com.webchat.webchat_be.service.ReportService;
import com.webchat.webchat_be.vo.ReportQueryVO;
import com.webchat.webchat_be.vo.ReportUpdateVO;
import com.webchat.webchat_be.vo.ReportVO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public String save(@Valid @RequestBody ReportVO vO) {
        return reportService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") Integer id) {
        reportService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") Integer id,
                       @Valid @RequestBody ReportUpdateVO vO) {
        reportService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ReportDTO getById(@Valid @NotNull @PathVariable("id") Integer id) {
        return reportService.getById(id);
    }

    @GetMapping
    public Page<ReportDTO> query(@Valid ReportQueryVO vO) {
        return reportService.query(vO);
    }
}
