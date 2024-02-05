package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserprojectsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "projectId can not null")
    private Integer projectId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String projectName;

    private String description;

    private Date startDate;

    private Date endDate;

}
