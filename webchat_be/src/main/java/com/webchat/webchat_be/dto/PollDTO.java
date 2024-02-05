package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PollDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer pollId;

    private Integer userId;

    private String question;

    private String options;

    private Date createdAt;

    private Date updatedAt;

}
