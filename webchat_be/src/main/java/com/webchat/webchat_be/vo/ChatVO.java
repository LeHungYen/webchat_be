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

    @NotNull(message = "participant1Id can not null")
    private Integer participant1Id;

    @NotNull(message = "participant2Id can not null")
    private Integer participant2Id;

    private Date createdAt;

}
