package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserprojectsDTO;
import com.webchat.webchat_be.entity.Userprojects;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserprojectsRepository;
import com.webchat.webchat_be.vo.UserprojectsQueryVO;
import com.webchat.webchat_be.vo.UserprojectsUpdateVO;
import com.webchat.webchat_be.vo.UserprojectsVO;

import java.util.NoSuchElementException;

@Service
public class UserprojectsService {

    @Autowired
    private UserprojectsRepository userprojectsRepository;

    public Integer save(UserprojectsVO vO) {
        Userprojects bean = new Userprojects();
        BeanUtils.copyProperties(vO, bean);
        bean = userprojectsRepository.save(bean);
        return bean.getProjectId();
    }

    public void delete(Integer id) {
        userprojectsRepository.deleteById(id);
    }

    public void update(Integer id, UserprojectsUpdateVO vO) {
        Userprojects bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userprojectsRepository.save(bean);
    }

    public UserprojectsDTO getById(Integer id) {
        Userprojects original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserprojectsDTO> query(UserprojectsQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserprojectsDTO toDTO(Userprojects original) {
        UserprojectsDTO bean = new UserprojectsDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userprojects requireOne(Integer id) {
        return userprojectsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
