package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ChatmessageQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer chatMessageId;
    private Integer replyToMessageId;

    private Integer chatId;

    private Integer chatParticipantId;

    private String content;

    private String mediaType;

    private String mediaURL;

    private Date createdAt;

    private String status;

}
