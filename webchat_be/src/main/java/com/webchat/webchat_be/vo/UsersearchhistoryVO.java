package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UsersearchhistoryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "searchHistoryId can not null")
    private Integer searchHistoryId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String searchQuery;

    private Date searchTimestamp;

}
