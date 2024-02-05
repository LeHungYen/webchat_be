package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserfeedbackVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "feedbackId can not null")
    private Integer feedbackId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String subject;

    private String feedbackType;

    private String content;

    private Date createdAt;

}
