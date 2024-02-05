package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserwallVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "wallId can not null")
    private Integer wallId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    @NotNull(message = "postId can not null")
    private Integer postId;

    private Date createdAt;

}
