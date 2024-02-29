package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.ChatMessageParticipantDTO;
import com.webchat.webchat_be.entity.ChatMessageParticipant;
import com.webchat.webchat_be.enums.ChatMessageParticipantStatus;
import com.webchat.webchat_be.repository.ChatMessageParticipantRepository;
import com.webchat.webchat_be.vo.ChatMessageParticipantQueryVO;
import com.webchat.webchat_be.vo.ChatMessageParticipantUpdateVO;
import com.webchat.webchat_be.vo.ChatMessageParticipantVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public List<ChatMessageParticipant> findByChatMessageIdAndChatParticipantId(int chatMessageId, int chatParticipantId) {
        return chatMessageParticipantRepository.getByChatMessageIdAndChatParticipantId(chatMessageId,chatParticipantId);
    }

    public List<ChatMessageParticipant> findByChatParticipantIdAndStatusAndChatId(int chatParticipantId , String status , int chatId) {
        return chatMessageParticipantRepository.getByChatParticipantIdAndStatusAndChatmessageChatId(chatParticipantId, status , chatId);
    }

//    public void setStatusReceivedToWatched(int chatParticipantId , int chatId) {
//        List<ChatMessageParticipant> chatMessageParticipants = findByChatParticipantIdAndStatusAndChatId(chatParticipantId , String.valueOf(ChatMessageParticipantStatus.RECEIVED) , chatId);
//
//        System.out.println(chatMessageParticipants);
//
//        if (!chatMessageParticipants.isEmpty()) {
//            chatMessageParticipants.forEach(chatMessageParticipant -> {
//                chatMessageParticipant.setStatus(String.valueOf(ChatMessageParticipantStatus.WATCHED));
//            });
//            chatMessageParticipantRepository.saveAll(chatMessageParticipants);
//        }
//    }

    public List<ChatMessageParticipant> getLastViewedMessage (int chatParticipantId , int chatId){
        return chatMessageParticipantRepository.
                getByChatParticipantIdAndChatmessageChatIdAndLastViewedAtIsNotNull
                        (chatParticipantId,chatId);
    }

    public List<ChatMessageParticipantDTO> getByChatMessageId (int chatMessageId){
        List<ChatMessageParticipant> chatMessageParticipants =
                chatMessageParticipantRepository.getAllByChatMessageId(chatMessageId);

        List<ChatMessageParticipantDTO> chatMessageParticipantDTOs = chatMessageParticipants.
                stream().map(chatMessageParticipant -> {
                    ChatMessageParticipantDTO dto = new ChatMessageParticipantDTO();
                    BeanUtils.copyProperties(chatMessageParticipant, dto);

                    dto.setFirstName(chatMessageParticipant.getChatParticipant().getUser().getFirstName());
                    dto.setLastName(chatMessageParticipant.getChatParticipant().getUser().getLastName());
                    dto.setUserProfilePicture(chatMessageParticipant.getChatParticipant().getUser().getProfilePicture());
//                    dto.setChatParticipantIdOfSender(chatMessageParticipant.getChatmessage().getChatParticipantId());
                    return dto;
                }).collect(Collectors.toList());;

        return chatMessageParticipantDTOs;
    }

    public void setLastViewedMessageToNull (int chatParticipantId , int chatId){
        List<ChatMessageParticipant> chatMessageParticipants = getLastViewedMessage(chatParticipantId , chatId);
        chatMessageParticipants.forEach(chatMessageParticipant -> {
                chatMessageParticipant.setLastViewedAt(null);
            });
            chatMessageParticipantRepository.saveAll(chatMessageParticipants);
    }

    public ChatMessageParticipant getLatestChatMessageParticipant (int chatParticipantId){
        return    chatMessageParticipantRepository.
                getTopByChatParticipantIdOrderByChatMessageIdDesc(chatParticipantId);
    }

    public boolean setLastViewedMessage(int chatParticipantId , int chatId) {
        ChatMessageParticipant chatMessageParticipant =
                chatMessageParticipantRepository.
                        getTopByChatParticipantIdOrderByChatMessageIdDesc(chatParticipantId);

       if(chatMessageParticipant.getLastViewedAt() == null){
           // clear last viewed message
           setLastViewedMessageToNull(chatParticipantId , chatId);

           // save viewd message
           chatMessageParticipant.setLastViewedAt(new Date());
           chatMessageParticipantRepository.save(chatMessageParticipant);
           return true;
       }
       return false;
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
