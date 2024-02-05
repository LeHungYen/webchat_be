package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class NotificationVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "notificationId can not null")
    private Integer notificationId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String type;

    private String link;

    private Integer isRead;

    private Date createdAt;

}
