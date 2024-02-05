package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UsersearchhistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer searchHistoryId;

    private Integer userId;

    private String searchQuery;

    private Date searchTimestamp;

}
