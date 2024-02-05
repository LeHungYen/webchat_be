package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserfeedbackDTO;
import com.webchat.webchat_be.entity.Userfeedback;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserfeedbackRepository;
import com.webchat.webchat_be.vo.UserfeedbackQueryVO;
import com.webchat.webchat_be.vo.UserfeedbackUpdateVO;
import com.webchat.webchat_be.vo.UserfeedbackVO;

import java.util.NoSuchElementException;

@Service
public class UserfeedbackService {

    @Autowired
    private UserfeedbackRepository userfeedbackRepository;

    public Integer save(UserfeedbackVO vO) {
        Userfeedback bean = new Userfeedback();
        BeanUtils.copyProperties(vO, bean);
        bean = userfeedbackRepository.save(bean);
        return bean.getFeedbackId();
    }

    public void delete(Integer id) {
        userfeedbackRepository.deleteById(id);
    }

    public void update(Integer id, UserfeedbackUpdateVO vO) {
        Userfeedback bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userfeedbackRepository.save(bean);
    }

    public UserfeedbackDTO getById(Integer id) {
        Userfeedback original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserfeedbackDTO> query(UserfeedbackQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserfeedbackDTO toDTO(Userfeedback original) {
        UserfeedbackDTO bean = new UserfeedbackDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userfeedback requireOne(Integer id) {
        return userfeedbackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
