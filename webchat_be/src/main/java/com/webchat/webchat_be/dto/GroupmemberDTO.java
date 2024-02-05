package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GroupmemberDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer groupMemberId;

    private Integer groupId;

    private Integer userId;

    private Date joinDate;

    private String role;

    private String status;

}
