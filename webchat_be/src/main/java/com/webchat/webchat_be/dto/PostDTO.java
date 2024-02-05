package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PostDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer postId;

    private Integer userId;

    private String content;

    private String mediaType;

    private String mediaURL;

    private String tag;

    private String location;

    private String privacy;

    private Date createAt;

    private Date updatedAt;

}
