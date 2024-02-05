package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
public class UserhobbieVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "hobbyId can not null")
    private Integer hobbyId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String hobbyName;

    private String description;

}
