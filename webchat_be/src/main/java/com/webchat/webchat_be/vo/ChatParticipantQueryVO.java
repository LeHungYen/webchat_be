package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
public class ChatParticipantQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer chatParticipantId;
    private Integer chatId;
    private Integer userId;
    private Date lastMessageSentAt;
    private Date joinedAt;
}
