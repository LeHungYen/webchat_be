package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserphotoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer photoId;

    private Integer albumId;

    private Integer userId;

    private String photoURL;

    private String caption;

    private Date createdAt;

}
