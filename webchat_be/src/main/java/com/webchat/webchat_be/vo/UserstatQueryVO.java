package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserstatQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer statId;

    private Integer userId;

    private Integer postCount;

    private Integer friendCount;

    private Integer followerCount;

    private Integer followingCount;

    private Date lastUpdate;

}
