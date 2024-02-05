package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.ChatmessageDTO;
import com.webchat.webchat_be.entity.Chatmessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.ChatmessageRepository;
import com.webchat.webchat_be.vo.ChatmessageQueryVO;
import com.webchat.webchat_be.vo.ChatmessageUpdateVO;
import com.webchat.webchat_be.vo.ChatmessageVO;

import java.util.NoSuchElementException;

@Service
public class ChatmessageService {

    @Autowired
    private ChatmessageRepository chatmessageRepository;

    public Integer save(ChatmessageVO vO) {
        Chatmessage bean = new Chatmessage();
        BeanUtils.copyProperties(vO, bean);
        bean = chatmessageRepository.save(bean);
        return bean.getChatMessageId();
    }

    public void delete(Integer id) {
        chatmessageRepository.deleteById(id);
    }

    public void update(Integer id, ChatmessageUpdateVO vO) {
        Chatmessage bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        chatmessageRepository.save(bean);
    }

    public ChatmessageDTO getById(Integer id) {
        Chatmessage original = requireOne(id);
        return toDTO(original);
    }

    public Page<ChatmessageDTO> query(ChatmessageQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ChatmessageDTO toDTO(Chatmessage original) {
        ChatmessageDTO bean = new ChatmessageDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Chatmessage requireOne(Integer id) {
        return chatmessageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
