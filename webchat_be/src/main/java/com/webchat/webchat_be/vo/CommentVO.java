package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class CommentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "commentId can not null")
    private Integer commentId;

    @NotNull(message = "postId can not null")
    private Integer postId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String content;

    private Date createAt;

    private Date updatedAt;

}
