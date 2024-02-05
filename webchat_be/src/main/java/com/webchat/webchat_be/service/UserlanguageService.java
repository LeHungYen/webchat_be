package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserlanguageDTO;
import com.webchat.webchat_be.entity.Userlanguage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserlanguageRepository;
import com.webchat.webchat_be.vo.UserlanguageQueryVO;
import com.webchat.webchat_be.vo.UserlanguageUpdateVO;
import com.webchat.webchat_be.vo.UserlanguageVO;

import java.util.NoSuchElementException;

@Service
public class UserlanguageService {

    @Autowired
    private UserlanguageRepository userlanguageRepository;

    public Integer save(UserlanguageVO vO) {
        Userlanguage bean = new Userlanguage();
        BeanUtils.copyProperties(vO, bean);
        bean = userlanguageRepository.save(bean);
        return bean.getLanguageId();
    }

    public void delete(Integer id) {
        userlanguageRepository.deleteById(id);
    }

    public void update(Integer id, UserlanguageUpdateVO vO) {
        Userlanguage bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userlanguageRepository.save(bean);
    }

    public UserlanguageDTO getById(Integer id) {
        Userlanguage original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserlanguageDTO> query(UserlanguageQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserlanguageDTO toDTO(Userlanguage original) {
        UserlanguageDTO bean = new UserlanguageDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userlanguage requireOne(Integer id) {
        return userlanguageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
