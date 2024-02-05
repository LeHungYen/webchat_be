package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserrewardDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer rewardId;

    private Integer userId;

    private String rewardType;

    private String description;

    private Date createdAt;

}
