package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FriendshipQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer friendshipId;

    private Integer userId1;

    private Integer userId2;

    private String status;

    private Date createAt;

    private Date updatedAt;

}
