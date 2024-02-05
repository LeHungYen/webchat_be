package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UsersettingDTO;
import com.webchat.webchat_be.entity.Usersetting;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UsersettingRepository;
import com.webchat.webchat_be.vo.UsersettingQueryVO;
import com.webchat.webchat_be.vo.UsersettingUpdateVO;
import com.webchat.webchat_be.vo.UsersettingVO;

import java.util.NoSuchElementException;

@Service
public class UsersettingService {

    @Autowired
    private UsersettingRepository usersettingRepository;

    public Integer save(UsersettingVO vO) {
        Usersetting bean = new Usersetting();
        BeanUtils.copyProperties(vO, bean);
        bean = usersettingRepository.save(bean);
        return bean.getSettingId();
    }

    public void delete(Integer id) {
        usersettingRepository.deleteById(id);
    }

    public void update(Integer id, UsersettingUpdateVO vO) {
        Usersetting bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        usersettingRepository.save(bean);
    }

    public UsersettingDTO getById(Integer id) {
        Usersetting original = requireOne(id);
        return toDTO(original);
    }

    public Page<UsersettingDTO> query(UsersettingQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UsersettingDTO toDTO(Usersetting original) {
        UsersettingDTO bean = new UsersettingDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Usersetting requireOne(Integer id) {
        return usersettingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
