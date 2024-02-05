package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UsersearchhistoryDTO;
import com.webchat.webchat_be.entity.Usersearchhistory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UsersearchhistoryRepository;
import com.webchat.webchat_be.vo.UsersearchhistoryQueryVO;
import com.webchat.webchat_be.vo.UsersearchhistoryUpdateVO;
import com.webchat.webchat_be.vo.UsersearchhistoryVO;

import java.util.NoSuchElementException;

@Service
public class UsersearchhistoryService {

    @Autowired
    private UsersearchhistoryRepository usersearchhistoryRepository;

    public Integer save(UsersearchhistoryVO vO) {
        Usersearchhistory bean = new Usersearchhistory();
        BeanUtils.copyProperties(vO, bean);
        bean = usersearchhistoryRepository.save(bean);
        return bean.getSearchHistoryId();
    }

    public void delete(Integer id) {
        usersearchhistoryRepository.deleteById(id);
    }

    public void update(Integer id, UsersearchhistoryUpdateVO vO) {
        Usersearchhistory bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        usersearchhistoryRepository.save(bean);
    }

    public UsersearchhistoryDTO getById(Integer id) {
        Usersearchhistory original = requireOne(id);
        return toDTO(original);
    }

    public Page<UsersearchhistoryDTO> query(UsersearchhistoryQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UsersearchhistoryDTO toDTO(Usersearchhistory original) {
        UsersearchhistoryDTO bean = new UsersearchhistoryDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Usersearchhistory requireOne(Integer id) {
        return usersearchhistoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
