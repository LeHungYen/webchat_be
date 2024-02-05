package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UservideoQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer videoId;

    private Integer userId;

    private String videoURL;

    private String caption;

    private Date createdAt;

}
