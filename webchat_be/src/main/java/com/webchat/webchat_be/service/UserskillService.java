package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserskillDTO;
import com.webchat.webchat_be.entity.Userskill;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserskillRepository;
import com.webchat.webchat_be.vo.UserskillQueryVO;
import com.webchat.webchat_be.vo.UserskillUpdateVO;
import com.webchat.webchat_be.vo.UserskillVO;

import java.util.NoSuchElementException;

@Service
public class UserskillService {

    @Autowired
    private UserskillRepository userskillRepository;

    public Integer save(UserskillVO vO) {
        Userskill bean = new Userskill();
        BeanUtils.copyProperties(vO, bean);
        bean = userskillRepository.save(bean);
        return bean.getSkillId();
    }

    public void delete(Integer id) {
        userskillRepository.deleteById(id);
    }

    public void update(Integer id, UserskillUpdateVO vO) {
        Userskill bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userskillRepository.save(bean);
    }

    public UserskillDTO getById(Integer id) {
        Userskill original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserskillDTO> query(UserskillQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserskillDTO toDTO(Userskill original) {
        UserskillDTO bean = new UserskillDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userskill requireOne(Integer id) {
        return userskillRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
