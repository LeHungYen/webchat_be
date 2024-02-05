package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserfavoriteVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "favoriteId can not null")
    private Integer favoriteId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    @NotNull(message = "targetId can not null")
    private Integer targetId;

    private String favoriteType;

    private Date createdAt;

}
