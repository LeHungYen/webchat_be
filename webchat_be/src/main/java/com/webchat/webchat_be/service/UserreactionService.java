package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserreactionDTO;
import com.webchat.webchat_be.entity.Userreaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserreactionRepository;
import com.webchat.webchat_be.vo.UserreactionQueryVO;
import com.webchat.webchat_be.vo.UserreactionUpdateVO;
import com.webchat.webchat_be.vo.UserreactionVO;

import java.util.NoSuchElementException;

@Service
public class UserreactionService {

    @Autowired
    private UserreactionRepository userreactionRepository;

    public Integer save(UserreactionVO vO) {
        Userreaction bean = new Userreaction();
        BeanUtils.copyProperties(vO, bean);
        bean = userreactionRepository.save(bean);
        return bean.getReactionId();
    }

    public void delete(Integer id) {
        userreactionRepository.deleteById(id);
    }

    public void update(Integer id, UserreactionUpdateVO vO) {
        Userreaction bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userreactionRepository.save(bean);
    }

    public UserreactionDTO getById(Integer id) {
        Userreaction original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserreactionDTO> query(UserreactionQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserreactionDTO toDTO(Userreaction original) {
        UserreactionDTO bean = new UserreactionDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userreaction requireOne(Integer id) {
        return userreactionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
