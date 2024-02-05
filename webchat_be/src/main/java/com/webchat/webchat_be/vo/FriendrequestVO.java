package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class FriendrequestVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "requestId can not null")
    private Integer requestId;

    @NotNull(message = "senderUserId can not null")
    private Integer senderUserId;

    @NotNull(message = "receiverUserId can not null")
    private Integer receiverUserId;

    private String status;

    private Date createdAt;

}
