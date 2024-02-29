package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class ChatVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "chatId can not null")
    private Integer chatId;
    private String type;
    private String name;
    private String avatar;
    private String emoji;
    private Date createdAt;
    private String updateType;
    private int chatParticipantId;

}
