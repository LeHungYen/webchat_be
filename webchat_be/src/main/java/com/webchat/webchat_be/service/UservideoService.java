package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UservideoDTO;
import com.webchat.webchat_be.entity.Uservideo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UservideoRepository;
import com.webchat.webchat_be.vo.UservideoQueryVO;
import com.webchat.webchat_be.vo.UservideoUpdateVO;
import com.webchat.webchat_be.vo.UservideoVO;

import java.util.NoSuchElementException;

@Service
public class UservideoService {

    @Autowired
    private UservideoRepository uservideoRepository;

    public Integer save(UservideoVO vO) {
        Uservideo bean = new Uservideo();
        BeanUtils.copyProperties(vO, bean);
        bean = uservideoRepository.save(bean);
        return bean.getVideoId();
    }

    public void delete(Integer id) {
        uservideoRepository.deleteById(id);
    }

    public void update(Integer id, UservideoUpdateVO vO) {
        Uservideo bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        uservideoRepository.save(bean);
    }

    public UservideoDTO getById(Integer id) {
        Uservideo original = requireOne(id);
        return toDTO(original);
    }

    public Page<UservideoDTO> query(UservideoQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UservideoDTO toDTO(Uservideo original) {
        UservideoDTO bean = new UservideoDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Uservideo requireOne(Integer id) {
        return uservideoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
