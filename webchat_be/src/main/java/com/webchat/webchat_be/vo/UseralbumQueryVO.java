package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UseralbumQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer albumId;

    private Integer userId;

    private String albumName;

    private String description;

    private Date createdAt;

}
