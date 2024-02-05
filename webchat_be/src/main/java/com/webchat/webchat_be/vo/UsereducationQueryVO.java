package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class UsereducationQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer educationId;

    private Integer userId;

    private String schoolName;

    private String degree;

    private String fieldOfStudy;

    private Integer graduationYear;

}
