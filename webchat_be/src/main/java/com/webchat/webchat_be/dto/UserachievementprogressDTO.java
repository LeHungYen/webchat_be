package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserachievementprogressDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer achievementProgressId;

    private Integer achievementId;

    private Integer userId;

    private BigDecimal progressValue;

    private Date updatedAt;

}
