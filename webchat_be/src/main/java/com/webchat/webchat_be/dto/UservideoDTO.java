package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UservideoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer videoId;

    private Integer userId;

    private String videoURL;

    private String caption;

    private Date createdAt;

}
