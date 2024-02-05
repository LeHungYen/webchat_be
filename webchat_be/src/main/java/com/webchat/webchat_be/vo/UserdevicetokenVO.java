package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserdevicetokenVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "deviceTokenId can not null")
    private Integer deviceTokenId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String deviceToken;

    private String deviceType;

    private Date timestamp;

}
