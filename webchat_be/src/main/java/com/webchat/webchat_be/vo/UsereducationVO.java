package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class UsereducationVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "educationId can not null")
    private Integer educationId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String schoolName;

    private String degree;

    private String fieldOfStudy;

    private Integer graduationYear;

}
