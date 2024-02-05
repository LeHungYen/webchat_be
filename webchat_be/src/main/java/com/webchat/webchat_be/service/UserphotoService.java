package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserphotoDTO;
import com.webchat.webchat_be.entity.Userphoto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserphotoRepository;
import com.webchat.webchat_be.vo.UserphotoQueryVO;
import com.webchat.webchat_be.vo.UserphotoUpdateVO;
import com.webchat.webchat_be.vo.UserphotoVO;

import java.util.NoSuchElementException;

@Service
public class UserphotoService {

    @Autowired
    private UserphotoRepository userphotoRepository;

    public Integer save(UserphotoVO vO) {
        Userphoto bean = new Userphoto();
        BeanUtils.copyProperties(vO, bean);
        bean = userphotoRepository.save(bean);
        return bean.getPhotoId();
    }

    public void delete(Integer id) {
        userphotoRepository.deleteById(id);
    }

    public void update(Integer id, UserphotoUpdateVO vO) {
        Userphoto bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userphotoRepository.save(bean);
    }

    public UserphotoDTO getById(Integer id) {
        Userphoto original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserphotoDTO> query(UserphotoQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserphotoDTO toDTO(Userphoto original) {
        UserphotoDTO bean = new UserphotoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userphoto requireOne(Integer id) {
        return userphotoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
