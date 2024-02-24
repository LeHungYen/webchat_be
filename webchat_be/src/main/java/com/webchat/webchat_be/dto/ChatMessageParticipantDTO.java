package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMessageParticipantDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer chatMessageParticipantId;

    private Integer chatMessageId;

    private Integer chatParticipantId;

    private String status;

}
