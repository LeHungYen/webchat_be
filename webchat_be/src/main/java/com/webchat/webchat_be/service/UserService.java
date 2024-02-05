package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserDTO;
import com.webchat.webchat_be.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserRepository;
import com.webchat.webchat_be.vo.UserQueryVO;
import com.webchat.webchat_be.vo.UserUpdateVO;
import com.webchat.webchat_be.vo.UserVO;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Integer save(UserVO vO) {
        User bean = new User();
        BeanUtils.copyProperties(vO, bean);
        bean = userRepository.save(bean);
        return bean.getUserId();
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void update(Integer id, UserUpdateVO vO) {
        User bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userRepository.save(bean);
    }

    public UserDTO getById(Integer id) {
        User original = requireOne(id);
        return toDTO(original);
    }

    public UserDTO getByEmailAndPassword(UserVO vO) {
        User original = userRepository.getUserByEmailAndPassword(vO.getEmail() , vO.getPassword()).orElseThrow(() -> new NoSuchElementException("Account not exist"));
        return toDTO(original);
    }

    public Page<UserDTO> query(UserQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserDTO toDTO(User original) {
        UserDTO bean = new UserDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private User requireOne(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
