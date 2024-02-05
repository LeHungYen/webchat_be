package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GroupmemberQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer groupMemberId;

    private Integer groupId;

    private Integer userId;

    private Date joinDate;

    private String role;

    private String status;

}
