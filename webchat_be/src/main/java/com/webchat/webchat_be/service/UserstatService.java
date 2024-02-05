package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserstatDTO;
import com.webchat.webchat_be.entity.Userstat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserstatRepository;
import com.webchat.webchat_be.vo.UserstatQueryVO;
import com.webchat.webchat_be.vo.UserstatUpdateVO;
import com.webchat.webchat_be.vo.UserstatVO;

import java.util.NoSuchElementException;

@Service
public class UserstatService {

    @Autowired
    private UserstatRepository userstatRepository;

    public Integer save(UserstatVO vO) {
        Userstat bean = new Userstat();
        BeanUtils.copyProperties(vO, bean);
        bean = userstatRepository.save(bean);
        return bean.getStatId();
    }

    public void delete(Integer id) {
        userstatRepository.deleteById(id);
    }

    public void update(Integer id, UserstatUpdateVO vO) {
        Userstat bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userstatRepository.save(bean);
    }

    public UserstatDTO getById(Integer id) {
        Userstat original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserstatDTO> query(UserstatQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserstatDTO toDTO(Userstat original) {
        UserstatDTO bean = new UserstatDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userstat requireOne(Integer id) {
        return userstatRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
