package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.PollDTO;
import com.webchat.webchat_be.entity.Poll;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.PollRepository;
import com.webchat.webchat_be.vo.PollQueryVO;
import com.webchat.webchat_be.vo.PollUpdateVO;
import com.webchat.webchat_be.vo.PollVO;

import java.util.NoSuchElementException;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public Integer save(PollVO vO) {
        Poll bean = new Poll();
        BeanUtils.copyProperties(vO, bean);
        bean = pollRepository.save(bean);
        return bean.getPollId();
    }

    public void delete(Integer id) {
        pollRepository.deleteById(id);
    }

    public void update(Integer id, PollUpdateVO vO) {
        Poll bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        pollRepository.save(bean);
    }

    public PollDTO getById(Integer id) {
        Poll original = requireOne(id);
        return toDTO(original);
    }

    public Page<PollDTO> query(PollQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private PollDTO toDTO(Poll original) {
        PollDTO bean = new PollDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Poll requireOne(Integer id) {
        return pollRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
