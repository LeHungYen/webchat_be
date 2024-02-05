package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserdevicetokenDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer deviceTokenId;

    private Integer userId;

    private String deviceToken;

    private String deviceType;

    private Date timestamp;

}
