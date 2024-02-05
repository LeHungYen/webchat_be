package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserlanguageQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer languageId;

    private Integer userId;

    private String languageName;

    private String proficiencyLevel;

}
