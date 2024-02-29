package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.ChatDTO;
import com.webchat.webchat_be.dto.ChatmessageDTO;
import com.webchat.webchat_be.entity.Chat;
import com.webchat.webchat_be.entity.ChatMessageParticipant;
import com.webchat.webchat_be.entity.ChatParticipant;
import com.webchat.webchat_be.entity.Chatmessage;
import com.webchat.webchat_be.enums.ChatMessageMediaType;
import com.webchat.webchat_be.enums.ChatMessageParticipantStatus;
import com.webchat.webchat_be.enums.ChatMessageType;
import com.webchat.webchat_be.utilities.Utilities;
import com.webchat.webchat_be.vo.ChatmessageVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.messaging.core.AbstractDestinationResolvingMessagingTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.ChatRepository;
import com.webchat.webchat_be.vo.ChatQueryVO;
import com.webchat.webchat_be.vo.ChatUpdateVO;
import com.webchat.webchat_be.vo.ChatVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static com.webchat.webchat_be.utilities.Utilities.saveFile;

@Service
public class ChatService {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatmessageService chatmessageService;

    @Autowired
    private ChatParticipantService chatParticipantService;

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

        // create chatMessage
        ChatMessageType chatMessageType = ChatMessageType.valueOf(vO.getUpdateType());


        ChatmessageVO chatmessageVO = new ChatmessageVO();
        chatmessageVO.setChatMessageId(null);
        chatmessageVO.setChatId(vO.getChatId());
        chatmessageVO.setChatParticipantId(vO.getChatParticipantId());
        chatmessageVO.setReplyToMessageId(null);
        chatmessageVO.setContent("");
        chatmessageVO.setMediaType(null);
        chatmessageVO.setMediaURL(null);

        if(chatMessageType.equals(ChatMessageType.CHANGE_NAME)){
            chatmessageVO.setContent(vO.getName());
        }else{
            chatmessageVO.setContent("");
        }


        chatmessageVO.setType(String.valueOf(chatMessageType));
        chatmessageService.save(chatmessageVO);


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

    public ChatmessageDTO saveImg(int chatId, int chatParticipantId, MultipartFile file) throws IOException {
        String mediaName = Utilities.saveFile(file);

        Chat original = requireOne(chatId);
        original.setAvatar(mediaName);
        chatRepository.save(original);

        ChatmessageVO chatmessageVO = new ChatmessageVO();
        chatmessageVO.setChatMessageId(null);
        chatmessageVO.setChatId(chatId);
        chatmessageVO.setChatParticipantId(chatParticipantId);
        chatmessageVO.setReplyToMessageId(null);
        chatmessageVO.setContent("");
        chatmessageVO.setMediaType(String.valueOf(ChatMessageMediaType.IMAGE));
        chatmessageVO.setMediaURL(mediaName);
        chatmessageVO.setType(String.valueOf(ChatMessageType.CHANGE_AVATAR));
        chatmessageService.save(chatmessageVO);

        return null;
    }
}
