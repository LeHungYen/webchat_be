package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class ChatmessageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "chatMessageId can not null")
    private Integer chatMessageId;

    @NotNull(message = "chatId can not null")
    private Integer chatId;

    @NotNull(message = "senderUserId can not null")
    private Integer chatParticipantId;

    private Integer replyToMessageId;

    private String content;

    private String mediaType;

    private String mediaURL;

    private Date createdAt;

    private String status;

}
