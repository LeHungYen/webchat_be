package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserreactionQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer reactionId;

    private Integer userId;

    private String targetType;

    private Integer targetidFk;

    private String reactionType;

    private Date createdAt;

}
