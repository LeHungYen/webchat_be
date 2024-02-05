package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserworkexperienceDTO;
import com.webchat.webchat_be.entity.Userworkexperience;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserworkexperienceRepository;
import com.webchat.webchat_be.vo.UserworkexperienceQueryVO;
import com.webchat.webchat_be.vo.UserworkexperienceUpdateVO;
import com.webchat.webchat_be.vo.UserworkexperienceVO;

import java.util.NoSuchElementException;

@Service
public class UserworkexperienceService {

    @Autowired
    private UserworkexperienceRepository userworkexperienceRepository;

    public Integer save(UserworkexperienceVO vO) {
        Userworkexperience bean = new Userworkexperience();
        BeanUtils.copyProperties(vO, bean);
        bean = userworkexperienceRepository.save(bean);
        return bean.getExperienceId();
    }

    public void delete(Integer id) {
        userworkexperienceRepository.deleteById(id);
    }

    public void update(Integer id, UserworkexperienceUpdateVO vO) {
        Userworkexperience bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userworkexperienceRepository.save(bean);
    }

    public UserworkexperienceDTO getById(Integer id) {
        Userworkexperience original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserworkexperienceDTO> query(UserworkexperienceQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserworkexperienceDTO toDTO(Userworkexperience original) {
        UserworkexperienceDTO bean = new UserworkexperienceDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userworkexperience requireOne(Integer id) {
        return userworkexperienceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
