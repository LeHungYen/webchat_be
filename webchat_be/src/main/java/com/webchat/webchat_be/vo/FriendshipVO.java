package com.webchat.webchat_be.vo;

import lombok.Data;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;


@Data
public class FriendshipVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "friendshipId can not null")
    private Integer friendshipId;

    @NotNull(message = "userId1 can not null")
    private Integer userId1;

    @NotNull(message = "userId2 can not null")
    private Integer userId2;

    private String status;

    private Date createAt;

    private Date updatedAt;

}
