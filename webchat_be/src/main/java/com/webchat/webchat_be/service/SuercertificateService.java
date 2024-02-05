package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.SuercertificateDTO;
import com.webchat.webchat_be.entity.UserCertificate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.SuercertificateRepository;
import com.webchat.webchat_be.vo.SuercertificateQueryVO;
import com.webchat.webchat_be.vo.SuercertificateUpdateVO;
import com.webchat.webchat_be.vo.SuercertificateVO;

import java.util.NoSuchElementException;

@Service
public class SuercertificateService {

    @Autowired
    private SuercertificateRepository suercertificateRepository;

    public Integer save(SuercertificateVO vO) {
        UserCertificate bean = new UserCertificate();
        BeanUtils.copyProperties(vO, bean);
        bean = suercertificateRepository.save(bean);
        return bean.getCertificateId();
    }

    public void delete(Integer id) {
        suercertificateRepository.deleteById(id);
    }

    public void update(Integer id, SuercertificateUpdateVO vO) {
        UserCertificate bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        suercertificateRepository.save(bean);
    }

    public SuercertificateDTO getById(Integer id) {
        UserCertificate original = requireOne(id);
        return toDTO(original);
    }

    public Page<SuercertificateDTO> query(SuercertificateQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private SuercertificateDTO toDTO(UserCertificate original) {
        SuercertificateDTO bean = new SuercertificateDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private UserCertificate requireOne(Integer id) {
        return suercertificateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
