package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.FriendshipDTO;
import com.webchat.webchat_be.entity.Friendship;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.FriendshipRepository;
import com.webchat.webchat_be.vo.FriendshipQueryVO;
import com.webchat.webchat_be.vo.FriendshipUpdateVO;
import com.webchat.webchat_be.vo.FriendshipVO;

import java.util.NoSuchElementException;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    public Integer save(FriendshipVO vO) {
        Friendship bean = new Friendship();
        BeanUtils.copyProperties(vO, bean);
        bean = friendshipRepository.save(bean);
        return bean.getFriendshipId();
    }

    public void delete(Integer id) {
        friendshipRepository.deleteById(id);
    }

    public void update(Integer id, FriendshipUpdateVO vO) {
        Friendship bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        friendshipRepository.save(bean);
    }

    public FriendshipDTO getById(Integer id) {
        Friendship original = requireOne(id);
        return toDTO(original);
    }

    public Page<FriendshipDTO> query(FriendshipQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FriendshipDTO toDTO(Friendship original) {
        FriendshipDTO bean = new FriendshipDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Friendship requireOne(Integer id) {
        return friendshipRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
