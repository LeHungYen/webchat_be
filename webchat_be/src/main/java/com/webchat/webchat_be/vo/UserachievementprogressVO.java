package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class UserachievementprogressVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "achievementProgressId can not null")
    private Integer achievementProgressId;

    @NotNull(message = "achievementId can not null")
    private Integer achievementId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private BigDecimal progressValue;

    private Date updatedAt;

}
