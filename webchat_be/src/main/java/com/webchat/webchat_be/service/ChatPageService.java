package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.ChatDTO;
import com.webchat.webchat_be.dto.ChatPageDTO;
import com.webchat.webchat_be.dto.ChatParticipantDTO;
import com.webchat.webchat_be.dto.FriendshipDTO;
import com.webchat.webchat_be.dto.UserDTO;
import com.webchat.webchat_be.dto.UserDTOChatPage;
import com.webchat.webchat_be.entity.Chat;
import com.webchat.webchat_be.entity.ChatParticipant;
import com.webchat.webchat_be.entity.User;
import com.webchat.webchat_be.repository.ChatRepository;
import com.webchat.webchat_be.utilities.Utilities;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Service
public class ChatPageService {
    @Autowired
    ChatParticipantService chatParticipantService;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    ChatmessageService chatmessageService;
    @Autowired
    FriendshipService friendshipService;

    public Page<ChatPageDTO> getChatPage(int page){
        UserDTO currentUser = Utilities.getUserDTOFromContext();
        Page<ChatParticipantDTO> chatParticipantDTOPage = chatParticipantService.findByUserId(currentUser.getUserId() , page , 20);

        Page<ChatPageDTO> chatPageDTOPage = chatParticipantDTOPage.map(chatParticipantDTO -> {
            Chat chat = chatRepository.findById(chatParticipantDTO.getChatId())
                    .orElseThrow(() -> new NoSuchElementException("Resource not found: " + chatParticipantDTO.getChatId()));;

           List<ChatParticipantDTO> chatParticipantDTOList =  chat.getChatParticipants().stream().map(chatParticipant -> {
               ChatParticipantDTO dto = new ChatParticipantDTO();
                BeanUtils.copyProperties(chatParticipant , dto);

                UserDTOChatPage userDTOChatPage = new UserDTOChatPage();
                BeanUtils.copyProperties(chatParticipant.getUser() , userDTOChatPage);

               // check is friend with user call this api
               FriendshipDTO friendshipDTO = friendshipService.findByUserId1AndUserId2(userDTOChatPage.getUserId(),currentUser.getUserId());
               if(friendshipDTO == null){
                   userDTOChatPage.setAlreadyBeFriend(false);
               }else{
                   userDTOChatPage.setAlreadyBeFriend(true);
               }

               dto.setUserDTO(userDTOChatPage);
                return dto;
            }).toList();

            ChatPageDTO chatPageDTO = ChatPageDTO.builder()
                    .chatId(chat.getChatId())
                    .type(chat.getType())
                    .name(chat.getName())
                    .createdAt(chat.getCreatedAt())
                    .chatParticipantOfCurrentUser(chatParticipantDTO)
                    .chatParticipants(chatParticipantDTOList)
                    .latestChatMessage(chatmessageService.getLatestChatMessageByChatId(chat.getChatId()))
                    .build();
            return chatPageDTO;
        });

        return chatPageDTOPage;
    }

}
