package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserfollowingDTO;
import com.webchat.webchat_be.entity.Userfollowing;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserfollowingRepository;
import com.webchat.webchat_be.vo.UserfollowingQueryVO;
import com.webchat.webchat_be.vo.UserfollowingUpdateVO;
import com.webchat.webchat_be.vo.UserfollowingVO;

import java.util.NoSuchElementException;

@Service
public class UserfollowingService {

    @Autowired
    private UserfollowingRepository userfollowingRepository;

    public Integer save(UserfollowingVO vO) {
        Userfollowing bean = new Userfollowing();
        BeanUtils.copyProperties(vO, bean);
        bean = userfollowingRepository.save(bean);
        return bean.getFollowingId();
    }

    public void delete(Integer id) {
        userfollowingRepository.deleteById(id);
    }

    public void update(Integer id, UserfollowingUpdateVO vO) {
        Userfollowing bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userfollowingRepository.save(bean);
    }

    public UserfollowingDTO getById(Integer id) {
        Userfollowing original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserfollowingDTO> query(UserfollowingQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserfollowingDTO toDTO(Userfollowing original) {
        UserfollowingDTO bean = new UserfollowingDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userfollowing requireOne(Integer id) {
        return userfollowingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
