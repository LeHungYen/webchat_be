package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.ReportDTO;
import com.webchat.webchat_be.entity.Report;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.ReportRepository;
import com.webchat.webchat_be.vo.ReportQueryVO;
import com.webchat.webchat_be.vo.ReportUpdateVO;
import com.webchat.webchat_be.vo.ReportVO;

import java.util.NoSuchElementException;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Integer save(ReportVO vO) {
        Report bean = new Report();
        BeanUtils.copyProperties(vO, bean);
        bean = reportRepository.save(bean);
        return bean.getReportId();
    }

    public void delete(Integer id) {
        reportRepository.deleteById(id);
    }

    public void update(Integer id, ReportUpdateVO vO) {
        Report bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        reportRepository.save(bean);
    }

    public ReportDTO getById(Integer id) {
        Report original = requireOne(id);
        return toDTO(original);
    }

    public Page<ReportDTO> query(ReportQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ReportDTO toDTO(Report original) {
        ReportDTO bean = new ReportDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Report requireOne(Integer id) {
        return reportRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
