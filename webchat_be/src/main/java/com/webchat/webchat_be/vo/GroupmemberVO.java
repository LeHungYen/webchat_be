package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class GroupmemberVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "groupMemberId can not null")
    private Integer groupMemberId;

    @NotNull(message = "groupId can not null")
    private Integer groupId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private Date joinDate;

    private String role;

    private String status;

}
