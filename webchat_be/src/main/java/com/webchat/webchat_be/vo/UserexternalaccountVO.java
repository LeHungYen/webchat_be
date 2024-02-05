package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class UserexternalaccountVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "externalAccountId can not null")
    private Integer externalAccountId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String provider;

    private String externalUserId;

}
