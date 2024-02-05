package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GroupDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer groupId;

    private String groupName;

    private String description;

    private Integer adminUserId;

    private Date createAt;

    private Date updatedAt;

}
