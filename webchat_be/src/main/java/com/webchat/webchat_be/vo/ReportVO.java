package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class ReportVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "reportId can not null")
    private Integer reportId;

    @NotNull(message = "reporterUserId can not null")
    private Integer reporterUserId;

    @NotNull(message = "reportedUserId can not null")
    private Integer reportedUserId;

    private Integer postId;

    private Integer commentId;

    private String type;

    private String description;

    private String status;

    private Date createdAt;

}
