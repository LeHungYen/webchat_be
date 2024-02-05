package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserwallDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer wallId;

    private Integer userId;

    private Integer postId;

    private Date createdAt;

}
