package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommentQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer commentId;

    private Integer postId;

    private Integer userId;

    private String content;

    private Date createAt;

    private Date updatedAt;

}
