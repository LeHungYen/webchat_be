package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserstatVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "statId can not null")
    private Integer statId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private Integer postCount;

    private Integer friendCount;

    private Integer followerCount;

    private Integer followingCount;

    private Date lastUpdate;

}
