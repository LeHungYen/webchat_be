package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserwallDTO;
import com.webchat.webchat_be.entity.Userwall;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserwallRepository;
import com.webchat.webchat_be.vo.UserwallQueryVO;
import com.webchat.webchat_be.vo.UserwallUpdateVO;
import com.webchat.webchat_be.vo.UserwallVO;

import java.util.NoSuchElementException;

@Service
public class UserwallService {

    @Autowired
    private UserwallRepository userwallRepository;

    public Integer save(UserwallVO vO) {
        Userwall bean = new Userwall();
        BeanUtils.copyProperties(vO, bean);
        bean = userwallRepository.save(bean);
        return bean.getWallId();
    }

    public void delete(Integer id) {
        userwallRepository.deleteById(id);
    }

    public void update(Integer id, UserwallUpdateVO vO) {
        Userwall bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userwallRepository.save(bean);
    }

    public UserwallDTO getById(Integer id) {
        Userwall original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserwallDTO> query(UserwallQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserwallDTO toDTO(Userwall original) {
        UserwallDTO bean = new UserwallDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userwall requireOne(Integer id) {
        return userwallRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
