package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserhobbieDTO;
import com.webchat.webchat_be.entity.Userhobbie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserhobbieRepository;
import com.webchat.webchat_be.vo.UserhobbieQueryVO;
import com.webchat.webchat_be.vo.UserhobbieUpdateVO;
import com.webchat.webchat_be.vo.UserhobbieVO;

import java.util.NoSuchElementException;

@Service
public class UserhobbieService {

    @Autowired
    private UserhobbieRepository userhobbieRepository;

    public Integer save(UserhobbieVO vO) {
        Userhobbie bean = new Userhobbie();
        BeanUtils.copyProperties(vO, bean);
        bean = userhobbieRepository.save(bean);
        return bean.getHobbyId();
    }

    public void delete(Integer id) {
        userhobbieRepository.deleteById(id);
    }

    public void update(Integer id, UserhobbieUpdateVO vO) {
        Userhobbie bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userhobbieRepository.save(bean);
    }

    public UserhobbieDTO getById(Integer id) {
        Userhobbie original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserhobbieDTO> query(UserhobbieQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserhobbieDTO toDTO(Userhobbie original) {
        UserhobbieDTO bean = new UserhobbieDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userhobbie requireOne(Integer id) {
        return userhobbieRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
