package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserlanguageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer languageId;

    private Integer userId;

    private String languageName;

    private String proficiencyLevel;

}
