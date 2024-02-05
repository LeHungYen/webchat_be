package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserexternalaccountQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer externalAccountId;

    private Integer userId;

    private String provider;

    private String externalUserId;

}
