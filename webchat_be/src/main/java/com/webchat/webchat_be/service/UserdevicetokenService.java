package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserdevicetokenDTO;
import com.webchat.webchat_be.entity.Userdevicetoken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserdevicetokenRepository;
import com.webchat.webchat_be.vo.UserdevicetokenQueryVO;
import com.webchat.webchat_be.vo.UserdevicetokenUpdateVO;
import com.webchat.webchat_be.vo.UserdevicetokenVO;

import java.util.NoSuchElementException;

@Service
public class UserdevicetokenService {

    @Autowired
    private UserdevicetokenRepository userdevicetokenRepository;

    public Integer save(UserdevicetokenVO vO) {
        Userdevicetoken bean = new Userdevicetoken();
        BeanUtils.copyProperties(vO, bean);
        bean = userdevicetokenRepository.save(bean);
        return bean.getDeviceTokenId();
    }

    public void delete(Integer id) {
        userdevicetokenRepository.deleteById(id);
    }

    public void update(Integer id, UserdevicetokenUpdateVO vO) {
        Userdevicetoken bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userdevicetokenRepository.save(bean);
    }

    public UserdevicetokenDTO getById(Integer id) {
        Userdevicetoken original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserdevicetokenDTO> query(UserdevicetokenQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserdevicetokenDTO toDTO(Userdevicetoken original) {
        UserdevicetokenDTO bean = new UserdevicetokenDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userdevicetoken requireOne(Integer id) {
        return userdevicetokenRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
