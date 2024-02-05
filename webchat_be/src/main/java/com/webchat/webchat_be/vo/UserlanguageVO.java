package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class UserlanguageVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "languageId can not null")
    private Integer languageId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String languageName;

    private String proficiencyLevel;

}
