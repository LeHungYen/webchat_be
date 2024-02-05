package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserhobbieQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer hobbyId;

    private Integer userId;

    private String hobbyName;

    private String description;

}
