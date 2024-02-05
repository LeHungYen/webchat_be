package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UseralbumDTO;
import com.webchat.webchat_be.entity.Useralbum;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UseralbumRepository;
import com.webchat.webchat_be.vo.UseralbumQueryVO;
import com.webchat.webchat_be.vo.UseralbumUpdateVO;
import com.webchat.webchat_be.vo.UseralbumVO;

import java.util.NoSuchElementException;

@Service
public class UseralbumService {

    @Autowired
    private UseralbumRepository useralbumRepository;

    public Integer save(UseralbumVO vO) {
        Useralbum bean = new Useralbum();
        BeanUtils.copyProperties(vO, bean);
        bean = useralbumRepository.save(bean);
        return bean.getAlbumId();
    }

    public void delete(Integer id) {
        useralbumRepository.deleteById(id);
    }

    public void update(Integer id, UseralbumUpdateVO vO) {
        Useralbum bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        useralbumRepository.save(bean);
    }

    public UseralbumDTO getById(Integer id) {
        Useralbum original = requireOne(id);
        return toDTO(original);
    }

    public Page<UseralbumDTO> query(UseralbumQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UseralbumDTO toDTO(Useralbum original) {
        UseralbumDTO bean = new UseralbumDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Useralbum requireOne(Integer id) {
        return useralbumRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
