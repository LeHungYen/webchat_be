package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UsersettingQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer settingId;

    private Integer userId;

    private String theme;

    private String notificationPreferences;

    private String language;

    private String privacySettings;

    private Date createdAt;

}
