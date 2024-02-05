package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.AdclickDTO;
import com.webchat.webchat_be.entity.Adclick;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.AdclickRepository;
import com.webchat.webchat_be.vo.AdclickQueryVO;
import com.webchat.webchat_be.vo.AdclickUpdateVO;
import com.webchat.webchat_be.vo.AdclickVO;

import java.util.NoSuchElementException;

@Service
public class AdclickService {

    @Autowired
    private AdclickRepository adclickRepository;

    public Integer save(AdclickVO vO) {
        Adclick bean = new Adclick();
        BeanUtils.copyProperties(vO, bean);
        bean = adclickRepository.save(bean);
        return bean.getClickId();
    }

    public void delete(Integer id) {
        adclickRepository.deleteById(id);
    }

    public void update(Integer id, AdclickUpdateVO vO) {
        Adclick bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        adclickRepository.save(bean);
    }

    public AdclickDTO getById(Integer id) {
        Adclick original = requireOne(id);
        return toDTO(original);
    }

    public Page<AdclickDTO> query(AdclickQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private AdclickDTO toDTO(Adclick original) {
        AdclickDTO bean = new AdclickDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Adclick requireOne(Integer id) {
        return adclickRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
