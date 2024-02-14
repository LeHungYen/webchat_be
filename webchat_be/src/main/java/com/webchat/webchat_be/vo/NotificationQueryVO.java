package com.webchat.webchat_be.vo;


import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NotificationQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer notificationId;

    private Integer senderId;

    private Integer receiverId;

    private String type;

    private String link;

    private boolean isRead;

    private Date createdAt;

}
