package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserrewardDTO;
import com.webchat.webchat_be.entity.Userreward;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserrewardRepository;
import com.webchat.webchat_be.vo.UserrewardQueryVO;
import com.webchat.webchat_be.vo.UserrewardUpdateVO;
import com.webchat.webchat_be.vo.UserrewardVO;

import java.util.NoSuchElementException;

@Service
public class UserrewardService {

    @Autowired
    private UserrewardRepository userrewardRepository;

    public Integer save(UserrewardVO vO) {
        Userreward bean = new Userreward();
        BeanUtils.copyProperties(vO, bean);
        bean = userrewardRepository.save(bean);
        return bean.getRewardId();
    }

    public void delete(Integer id) {
        userrewardRepository.deleteById(id);
    }

    public void update(Integer id, UserrewardUpdateVO vO) {
        Userreward bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userrewardRepository.save(bean);
    }

    public UserrewardDTO getById(Integer id) {
        Userreward original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserrewardDTO> query(UserrewardQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserrewardDTO toDTO(Userreward original) {
        UserrewardDTO bean = new UserrewardDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userreward requireOne(Integer id) {
        return userrewardRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
