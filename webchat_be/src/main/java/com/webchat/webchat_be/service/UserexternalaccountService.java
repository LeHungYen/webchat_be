package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserexternalaccountDTO;
import com.webchat.webchat_be.entity.Userexternalaccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserexternalaccountRepository;
import com.webchat.webchat_be.vo.UserexternalaccountQueryVO;
import com.webchat.webchat_be.vo.UserexternalaccountUpdateVO;
import com.webchat.webchat_be.vo.UserexternalaccountVO;

import java.util.NoSuchElementException;

@Service
public class UserexternalaccountService {

    @Autowired
    private UserexternalaccountRepository userexternalaccountRepository;

    public Integer save(UserexternalaccountVO vO) {
        Userexternalaccount bean = new Userexternalaccount();
        BeanUtils.copyProperties(vO, bean);
        bean = userexternalaccountRepository.save(bean);
        return bean.getExternalAccountId();
    }

    public void delete(Integer id) {
        userexternalaccountRepository.deleteById(id);
    }

    public void update(Integer id, UserexternalaccountUpdateVO vO) {
        Userexternalaccount bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userexternalaccountRepository.save(bean);
    }

    public UserexternalaccountDTO getById(Integer id) {
        Userexternalaccount original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserexternalaccountDTO> query(UserexternalaccountQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserexternalaccountDTO toDTO(Userexternalaccount original) {
        UserexternalaccountDTO bean = new UserexternalaccountDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userexternalaccount requireOne(Integer id) {
        return userexternalaccountRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
