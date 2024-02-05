package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer notificationId;

    private Integer userId;

    private String type;

    private String link;

    private Integer isRead;

    private Date createdAt;

}
