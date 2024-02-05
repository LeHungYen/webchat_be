package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class GroupVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "groupId can not null")
    private Integer groupId;

    private String groupName;

    private String description;

    @NotNull(message = "adminUserId can not null")
    private Integer adminUserId;

    private Date createAt;

    private Date updatedAt;

}
