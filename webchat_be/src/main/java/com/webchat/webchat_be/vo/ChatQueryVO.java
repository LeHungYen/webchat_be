package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ChatQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer chatId;

    private Integer participant1Id;

    private Integer participant2Id;

    private Date createdAt;

}
