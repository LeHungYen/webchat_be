package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UsereducationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer educationId;

    private Integer userId;

    private String schoolName;

    private String degree;

    private String fieldOfStudy;

    private Integer graduationYear;

}
