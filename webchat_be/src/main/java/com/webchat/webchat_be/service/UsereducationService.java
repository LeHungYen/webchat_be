package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UsereducationDTO;
import com.webchat.webchat_be.entity.Usereducation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UsereducationRepository;
import com.webchat.webchat_be.vo.UsereducationQueryVO;
import com.webchat.webchat_be.vo.UsereducationUpdateVO;
import com.webchat.webchat_be.vo.UsereducationVO;

import java.util.NoSuchElementException;

@Service
public class UsereducationService {

    @Autowired
    private UsereducationRepository usereducationRepository;

    public Integer save(UsereducationVO vO) {
        Usereducation bean = new Usereducation();
        BeanUtils.copyProperties(vO, bean);
        bean = usereducationRepository.save(bean);
        return bean.getEducationId();
    }

    public void delete(Integer id) {
        usereducationRepository.deleteById(id);
    }

    public void update(Integer id, UsereducationUpdateVO vO) {
        Usereducation bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        usereducationRepository.save(bean);
    }

    public UsereducationDTO getById(Integer id) {
        Usereducation original = requireOne(id);
        return toDTO(original);
    }

    public Page<UsereducationDTO> query(UsereducationQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UsereducationDTO toDTO(Usereducation original) {
        UsereducationDTO bean = new UsereducationDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Usereducation requireOne(Integer id) {
        return usereducationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
