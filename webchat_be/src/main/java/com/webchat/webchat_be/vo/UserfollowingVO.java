package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserfollowingVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "followingId can not null")
    private Integer followingId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    @NotNull(message = "followingUserId can not null")
    private Integer followingUserId;

    private Date followDate;

}
