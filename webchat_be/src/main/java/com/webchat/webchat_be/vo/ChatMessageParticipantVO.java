package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class ChatMessageParticipantVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "chatMessageParticipantId can not null")
    private Integer chatMessageParticipantId;

    @NotNull(message = "chatMessageId can not null")
    private Integer chatMessageId;

    @NotNull(message = "chatParticipantId can not null")
    private Integer chatParticipantId;

    private String status;

}
