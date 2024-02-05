package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PollQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer pollId;

    private Integer userId;

    private String question;

    private String options;

    private Date createdAt;

    private Date updatedAt;

}
