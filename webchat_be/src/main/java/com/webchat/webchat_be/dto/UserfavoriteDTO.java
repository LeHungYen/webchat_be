package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserfavoriteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer favoriteId;

    private Integer userId;

    private Integer targetId;

    private String favoriteType;

    private Date createdAt;

}
