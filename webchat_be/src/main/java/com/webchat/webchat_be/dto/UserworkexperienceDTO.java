package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserworkexperienceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer experienceId;

    private Integer userId;

    private String companyName;

    private String position;

    private String employmentPeriod;

}
