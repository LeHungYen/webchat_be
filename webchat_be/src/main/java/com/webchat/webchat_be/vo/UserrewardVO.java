package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserrewardVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "rewardId can not null")
    private Integer rewardId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String rewardType;

    private String description;

    private Date createdAt;

}
