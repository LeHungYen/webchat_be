package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserskillDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer skillId;

    private Integer userId;

    private String skillName;

    private String skillLevel;

}
