package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserfeedbackQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer feedbackId;

    private Integer userId;

    private String subject;

    private String feedbackType;

    private String content;

    private Date createdAt;

}
