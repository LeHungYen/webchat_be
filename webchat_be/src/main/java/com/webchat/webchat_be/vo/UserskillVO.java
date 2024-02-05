package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class UserskillVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "skillId can not null")
    private Integer skillId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String skillName;

    private String skillLevel;

}
