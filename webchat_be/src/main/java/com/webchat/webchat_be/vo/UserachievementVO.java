package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserachievementVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "achievementId can not null")
    private Integer achievementId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String title;

    private String description;

    private Date achievedAt;

}
