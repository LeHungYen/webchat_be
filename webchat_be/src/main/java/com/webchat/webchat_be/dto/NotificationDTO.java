package com.webchat.webchat_be.dto;


import com.webchat.webchat_be.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer notificationId;

    private Integer senderId;

    private Integer receiverId;

    private String type;

    private String link;

    private boolean read;

    private Date createdAt;

    private UserDTO sender;

}
