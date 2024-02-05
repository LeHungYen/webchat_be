package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UsersettingVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "settingId can not null")
    private Integer settingId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String theme;

    private String notificationPreferences;

    private String language;

    private String privacySettings;

    private Date createdAt;

}
