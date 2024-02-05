package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserhobbieDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer hobbyId;

    private Integer userId;

    private String hobbyName;

    private String description;

}
