package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserachievementDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer achievementId;

    private Integer userId;

    private String title;

    private String description;

    private Date achievedAt;

}
