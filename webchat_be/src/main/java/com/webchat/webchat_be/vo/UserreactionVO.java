package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserreactionVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "reactionId can not null")
    private Integer reactionId;

    private Integer userId;

    private String targetType;

    private Integer targetidFk;

    private String reactionType;

    @NotNull(message = "createdAt can not null")
    private Date createdAt;

}
