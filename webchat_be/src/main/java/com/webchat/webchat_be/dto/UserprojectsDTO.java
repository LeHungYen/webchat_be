package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserprojectsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer projectId;

    private Integer userId;

    private String projectName;

    private String description;

    private Date startDate;

    private Date endDate;

}
