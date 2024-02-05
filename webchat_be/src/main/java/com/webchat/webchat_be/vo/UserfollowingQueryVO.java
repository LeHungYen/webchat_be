package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserfollowingQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer followingId;

    private Integer userId;

    private Integer followingUserId;

    private Date followDate;

}
