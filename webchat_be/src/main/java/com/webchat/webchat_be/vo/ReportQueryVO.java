package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ReportQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer reportId;

    private Integer reporterUserId;

    private Integer reportedUserId;

    private Integer postId;

    private Integer commentId;

    private String type;

    private String description;

    private String status;

    private Date createdAt;

}
