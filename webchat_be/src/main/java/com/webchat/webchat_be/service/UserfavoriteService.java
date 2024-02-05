package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserfavoriteDTO;
import com.webchat.webchat_be.entity.Userfavorite;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserfavoriteRepository;
import com.webchat.webchat_be.vo.UserfavoriteQueryVO;
import com.webchat.webchat_be.vo.UserfavoriteUpdateVO;
import com.webchat.webchat_be.vo.UserfavoriteVO;

import java.util.NoSuchElementException;

@Service
public class UserfavoriteService {

    @Autowired
    private UserfavoriteRepository userfavoriteRepository;

    public Integer save(UserfavoriteVO vO) {
        Userfavorite bean = new Userfavorite();
        BeanUtils.copyProperties(vO, bean);
        bean = userfavoriteRepository.save(bean);
        return bean.getFavoriteId();
    }

    public void delete(Integer id) {
        userfavoriteRepository.deleteById(id);
    }

    public void update(Integer id, UserfavoriteUpdateVO vO) {
        Userfavorite bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userfavoriteRepository.save(bean);
    }

    public UserfavoriteDTO getById(Integer id) {
        Userfavorite original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserfavoriteDTO> query(UserfavoriteQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserfavoriteDTO toDTO(Userfavorite original) {
        UserfavoriteDTO bean = new UserfavoriteDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userfavorite requireOne(Integer id) {
        return userfavoriteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
