package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserphotoQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer photoId;

    private Integer albumId;

    private Integer userId;

    private String photoURL;

    private String caption;

    private Date createdAt;

}
