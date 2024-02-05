package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserlocationhistoryDTO;
import com.webchat.webchat_be.entity.Userlocationhistory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserlocationhistoryRepository;
import com.webchat.webchat_be.vo.UserlocationhistoryQueryVO;
import com.webchat.webchat_be.vo.UserlocationhistoryUpdateVO;
import com.webchat.webchat_be.vo.UserlocationhistoryVO;

import java.util.NoSuchElementException;

@Service
public class UserlocationhistoryService {

    @Autowired
    private UserlocationhistoryRepository userlocationhistoryRepository;

    public Integer save(UserlocationhistoryVO vO) {
        Userlocationhistory bean = new Userlocationhistory();
        BeanUtils.copyProperties(vO, bean);
        bean = userlocationhistoryRepository.save(bean);
        return bean.getLocationHistoryId();
    }

    public void delete(Integer id) {
        userlocationhistoryRepository.deleteById(id);
    }

    public void update(Integer id, UserlocationhistoryUpdateVO vO) {
        Userlocationhistory bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userlocationhistoryRepository.save(bean);
    }

    public UserlocationhistoryDTO getById(Integer id) {
        Userlocationhistory original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserlocationhistoryDTO> query(UserlocationhistoryQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserlocationhistoryDTO toDTO(Userlocationhistory original) {
        UserlocationhistoryDTO bean = new UserlocationhistoryDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userlocationhistory requireOne(Integer id) {
        return userlocationhistoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
