package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.ChatParticipantDTO;
import com.webchat.webchat_be.entity.ChatParticipant;
import com.webchat.webchat_be.repository.ChatParticipantRepository;
import com.webchat.webchat_be.vo.ChatParticipantQueryVO;
import com.webchat.webchat_be.vo.ChatParticipantUpdateVO;
import com.webchat.webchat_be.vo.ChatParticipantVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ChatParticipantService {

    @Autowired
    private ChatParticipantRepository chatParticipantRepository;

    public Integer save(ChatParticipantVO vO) {
        ChatParticipant bean = new ChatParticipant();
        BeanUtils.copyProperties(vO, bean);
        bean.setJoinedAt(new Date());
        bean = chatParticipantRepository.save(bean);
        return bean.getChatParticipantId();
    }

    public void delete(Integer id) {
        chatParticipantRepository.deleteById(id);
    }

    public void update(Integer id, ChatParticipantUpdateVO vO) {
        ChatParticipant bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        chatParticipantRepository.save(bean);
    }

    public ChatParticipantDTO getById(Integer id) {
        ChatParticipant original = requireOne(id);
        return toDTO(original);
    }


    public Page<ChatParticipantDTO> findByUserId (int userId , int page , int size){
        Sort sort = Sort.by("lastMessageSentAt").descending();
        Pageable pageable = PageRequest.of(page , size , sort);
        Page<ChatParticipant> chatParticipantPage = chatParticipantRepository.getAllByUserId(userId , pageable);

        Page<ChatParticipantDTO> chatParticipantDTOPage = chatParticipantPage.map(chatParticipant -> {
            ChatParticipantDTO dto = new ChatParticipantDTO();
            BeanUtils.copyProperties(chatParticipant, dto);
            return dto;
        });

        return chatParticipantDTOPage;
    }

    public List<ChatParticipant> findByChatId (int chatId){
        return chatParticipantRepository.getAllByChatId(chatId);
    }

    private ChatParticipantDTO toDTO(ChatParticipant original) {
        ChatParticipantDTO bean = new ChatParticipantDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ChatParticipant requireOne(Integer id) {
        return chatParticipantRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
