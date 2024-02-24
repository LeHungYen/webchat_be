package com.webchat.webchat_be.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ChatPageDTO {

    private Integer chatId;
    private String type;
    private String name;
    private Date createdAt;
    List<ChatParticipantDTO> chatParticipants;
    ChatParticipantDTO chatParticipantOfCurrentUser;
    ChatmessageDTO latestChatMessage;
}
