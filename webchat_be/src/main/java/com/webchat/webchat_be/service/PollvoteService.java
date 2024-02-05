package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.PollvoteDTO;
import com.webchat.webchat_be.entity.Pollvote;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.PollvoteRepository;
import com.webchat.webchat_be.vo.PollvoteQueryVO;
import com.webchat.webchat_be.vo.PollvoteUpdateVO;
import com.webchat.webchat_be.vo.PollvoteVO;

import java.util.NoSuchElementException;

@Service
public class PollvoteService {

    @Autowired
    private PollvoteRepository pollvoteRepository;

    public Integer save(PollvoteVO vO) {
        Pollvote bean = new Pollvote();
        BeanUtils.copyProperties(vO, bean);
        bean = pollvoteRepository.save(bean);
        return bean.getVoteId();
    }

    public void delete(Integer id) {
        pollvoteRepository.deleteById(id);
    }

    public void update(Integer id, PollvoteUpdateVO vO) {
        Pollvote bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        pollvoteRepository.save(bean);
    }

    public PollvoteDTO getById(Integer id) {
        Pollvote original = requireOne(id);
        return toDTO(original);
    }

    public Page<PollvoteDTO> query(PollvoteQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private PollvoteDTO toDTO(Pollvote original) {
        PollvoteDTO bean = new PollvoteDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Pollvote requireOne(Integer id) {
        return pollvoteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
