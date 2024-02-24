package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class ChatParticipantVO implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull(message = "chatParticipantId can not null")
    private Integer chatParticipantId;
    @NotNull(message = "chatId can not null")
    private Integer chatId;
    @NotNull(message = "userId can not null")
    private Integer userId;
    private Date lastMessageSentAt;
    private Date joinedAt;
}
