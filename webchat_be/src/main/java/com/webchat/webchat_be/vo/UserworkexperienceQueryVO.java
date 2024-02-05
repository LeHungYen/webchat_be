package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserworkexperienceQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer experienceId;

    private Integer userId;

    private String companyName;

    private String position;

    private String employmentPeriod;

}
