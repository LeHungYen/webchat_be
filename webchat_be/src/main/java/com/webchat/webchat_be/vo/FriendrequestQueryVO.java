package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FriendrequestQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer requestId;

    private Integer senderUserId;

    private Integer receiverUserId;

    private String status;

    private Date createdAt;

}
