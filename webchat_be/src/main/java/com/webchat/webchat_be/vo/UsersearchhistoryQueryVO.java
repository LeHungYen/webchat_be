package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UsersearchhistoryQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer searchHistoryId;

    private Integer userId;

    private String searchQuery;

    private Date searchTimestamp;

}
