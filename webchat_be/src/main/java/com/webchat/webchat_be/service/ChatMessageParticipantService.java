package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.ChatMessageParticipantDTO;
import com.webchat.webchat_be.entity.ChatMessageParticipant;
import com.webchat.webchat_be.repository.ChatMessageParticipantRepository;
import com.webchat.webchat_be.vo.ChatMessageParticipantQueryVO;
import com.webchat.webchat_be.vo.ChatMessageParticipantUpdateVO;
import com.webchat.webchat_be.vo.ChatMessageParticipantVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ChatMessageParticipantService {

    @Autowired
    private ChatMessageParticipantRepository chatMessageParticipantRepository;

    public Integer save(ChatMessageParticipantVO vO) {
        ChatMessageParticipant bean = new ChatMessageParticipant();
        BeanUtils.copyProperties(vO, bean);
        bean = chatMessageParticipantRepository.save(bean);
        return bean.getChatMessageParticipantId();
    }

    public void delete(Integer id) {
        chatMessageParticipantRepository.deleteById(id);
    }

    public void update(Integer id, ChatMessageParticipantUpdateVO vO) {
        ChatMessageParticipant bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        chatMessageParticipantRepository.save(bean);
    }

    public ChatMessageParticipantDTO getById(Integer id) {
        ChatMessageParticipant original = requireOne(id);
        return toDTO(original);
    }

    public Page<ChatMessageParticipantDTO> query(ChatMessageParticipantQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ChatMessageParticipantDTO toDTO(ChatMessageParticipant original) {
        ChatMessageParticipantDTO bean = new ChatMessageParticipantDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ChatMessageParticipant requireOne(Integer id) {
        return chatMessageParticipantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
