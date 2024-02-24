package com.webchat.webchat_be.dto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ChatParticipantDTO {

    private Integer chatParticipantId;
    private Integer chatId;
    private Integer userId;
    private Date lastMessageSentAt;
    private Date joinedAt;
    private UserDTOChatPage userDTO;

}
