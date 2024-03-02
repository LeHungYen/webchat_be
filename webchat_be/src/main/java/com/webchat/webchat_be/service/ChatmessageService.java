package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.*;
import com.webchat.webchat_be.entity.ChatMessageParticipant;
import com.webchat.webchat_be.entity.ChatParticipant;
import com.webchat.webchat_be.entity.Chatmessage;
import com.webchat.webchat_be.enums.ChatMessageMediaType;
import com.webchat.webchat_be.enums.ChatMessageParticipantStatus;
import com.webchat.webchat_be.enums.ChatMessageStatus;
import com.webchat.webchat_be.repository.ChatMessageParticipantRepository;
import com.webchat.webchat_be.repository.ChatParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.ChatmessageRepository;
import com.webchat.webchat_be.vo.ChatmessageQueryVO;
import com.webchat.webchat_be.vo.ChatmessageUpdateVO;
import com.webchat.webchat_be.vo.ChatmessageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.webchat.webchat_be.utilities.Utilities.saveFile;

@Service
public class ChatmessageService {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    ChatmessageRepository chatmessageRepository;
    @Autowired
    ChatParticipantRepository chatParticipantRepository;
    @Autowired
    ChatParticipantService chatParticipantService;
    @Autowired
    ChatMessageParticipantRepository chatMessageParticipantRepository;
    @Autowired
    ChatMessageParticipantService chatMessageParticipantService;

    public ChatmessageDTO save(ChatmessageVO vO) {
        Chatmessage bean = new Chatmessage();
        BeanUtils.copyProperties(vO, bean);
        bean.setCreatedAt(new Date());
        bean = chatmessageRepository.save(bean);

        // update chat paticipant
        List<ChatParticipant> chatParticipants = chatParticipantService.findByChatId(vO.getChatId());
        for (ChatParticipant  chatParticipant: chatParticipants) {
            chatParticipant.setLastMessageSentAt(new Date());
            chatParticipantRepository.save(chatParticipant);

            // create chat message participant
            ChatMessageParticipant chatMessageParticipant = new ChatMessageParticipant();
            chatMessageParticipant.setChatMessageId(bean.getChatMessageId());
            chatMessageParticipant.setChatParticipantId(chatParticipant.getChatParticipantId());
            chatMessageParticipant.setStatus(String.valueOf(ChatMessageParticipantStatus.RECEIVED));
            chatMessageParticipantRepository.save(chatMessageParticipant);

            // send message to chat paticipant
            if(chatParticipant.getChatParticipantId() == bean.getChatParticipantId())
            simpMessagingTemplate.convertAndSend("/topic/chatMessage/"+ chatParticipant.getUserId() , vO.getChatId());
        }
        return toDTO(bean);
    }

    public ChatmessageDTO saveImg(int chatId , int chatParticipantId , MultipartFile file) throws IOException {

        Chatmessage bean = new Chatmessage();
        bean.setChatMessageId(null);
        bean.setChatId(chatId);
        bean.setChatParticipantId(chatParticipantId);
        bean.setReplyToMessageId(null);
        bean.setContent("");
        bean.setMediaType(String.valueOf(ChatMessageMediaType.IMAGE));
        bean.setMediaURL(saveFile(file));
        bean.setCreatedAt(new Date());
        bean = chatmessageRepository.save(bean);

        // update chat paticipant
        List<ChatParticipant> chatParticipants = chatParticipantService.findByChatId(chatId);
        for (ChatParticipant  chatParticipant: chatParticipants) {
            chatParticipant.setLastMessageSentAt(new Date());
            chatParticipantRepository.save(chatParticipant);

            // create chat message participant
            ChatMessageParticipant chatMessageParticipant = new ChatMessageParticipant();
            chatMessageParticipant.setChatMessageId(bean.getChatMessageId());
            chatMessageParticipant.setChatParticipantId(chatParticipant.getChatParticipantId());
            chatMessageParticipant.setStatus(String.valueOf(ChatMessageParticipantStatus.RECEIVED));
            chatMessageParticipantRepository.save(chatMessageParticipant);

            // send message to chat paticipant
            if(chatParticipant.getChatParticipantId() == bean.getChatParticipantId())
                simpMessagingTemplate.convertAndSend("/topic/chatMessage/"+ chatParticipant.getUserId() , bean.getChatId());
        }

        return null;
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

    public ChatmessageDTO getLatestChatMessageByChatId(int chatId) {
        Chatmessage chatmessage = chatmessageRepository.findTopByChatIdOrderByCreatedAtDesc(chatId);
        if(chatmessage != null){
            ChatmessageDTO dto = new ChatmessageDTO();
            BeanUtils.copyProperties(chatmessage , dto);
            dto.setLastName(chatmessage.getChatParticipant().getUser().getLastName());
            return dto;
        }
        return new ChatmessageDTO();
    }

    public Page<ChatmessageDTO> findAllByChatId (int chatId, int chatParticipantId , int pageNumber){
        // set last viewed message

        boolean isSetNewLastViewed = chatMessageParticipantService.setLastViewedMessage(chatParticipantId , chatId);

        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(pageNumber , 20 , sort);

        Page<Chatmessage> chatmessagePage = chatmessageRepository.findAllByChatId(chatId, pageable);
        Page<ChatmessageDTO> chatmessageDTOPage = chatmessagePage.map(chatmessage -> {

            List<ChatMessageParticipantDTO> chatMessageParticipantDTOs =
                    chatMessageParticipantService.getByChatMessageId(chatmessage.getChatMessageId());

            ChatmessageDTO dto = new ChatmessageDTO();
            BeanUtils.copyProperties(chatmessage , dto);
            dto.setLastName(chatmessage.getChatParticipant().getUser().getLastName());
            dto.setUserId(chatmessage.getChatParticipant().getUser().getUserId());
            dto.setChatMessageParticipantDTOs(chatMessageParticipantDTOs);
            return dto;
        });

        if(isSetNewLastViewed){
            List<ChatParticipant> chatParticipants = chatParticipantService.findByChatId(chatId);
            for (ChatParticipant  chatParticipant: chatParticipants) {
//                if(chatParticipantId != chatParticipant.getChatParticipantId())
                simpMessagingTemplate.convertAndSend("/topic/chatMessage/"+ chatParticipant.getUserId() , chatId);
            }
        }

        return chatmessageDTOPage;
    }
}
