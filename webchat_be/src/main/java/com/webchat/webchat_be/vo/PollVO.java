package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class PollVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "pollId can not null")
    private Integer pollId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String question;

    private String options;

    private Date createdAt;

    private Date updatedAt;

}
