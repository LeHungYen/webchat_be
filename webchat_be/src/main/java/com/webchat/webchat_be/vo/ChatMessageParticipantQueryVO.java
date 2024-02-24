package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class ChatMessageParticipantQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer chatMessageParticipantId;

    private Integer chatMessageId;

    private Integer chatParticipantId;

    private String status;

}
