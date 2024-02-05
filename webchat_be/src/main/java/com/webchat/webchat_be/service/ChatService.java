package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.ChatDTO;
import com.webchat.webchat_be.entity.Chat;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.ChatRepository;
import com.webchat.webchat_be.vo.ChatQueryVO;
import com.webchat.webchat_be.vo.ChatUpdateVO;
import com.webchat.webchat_be.vo.ChatVO;

import java.util.NoSuchElementException;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public Integer save(ChatVO vO) {
        Chat bean = new Chat();
        BeanUtils.copyProperties(vO, bean);
        bean = chatRepository.save(bean);
        return bean.getChatId();
    }

    public void delete(Integer id) {
        chatRepository.deleteById(id);
    }

    public void update(Integer id, ChatUpdateVO vO) {
        Chat bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        chatRepository.save(bean);
    }

    public ChatDTO getById(Integer id) {
        Chat original = requireOne(id);
        return toDTO(original);
    }

    public Page<ChatDTO> query(ChatQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ChatDTO toDTO(Chat original) {
        ChatDTO bean = new ChatDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Chat requireOne(Integer id) {
        return chatRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
