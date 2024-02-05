package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class UserworkexperienceVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "experienceId can not null")
    private Integer experienceId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String companyName;

    private String position;

    private String employmentPeriod;

}
