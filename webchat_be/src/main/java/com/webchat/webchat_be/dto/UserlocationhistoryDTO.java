package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class UserlocationhistoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer locationHistoryId;

    private Integer userId;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Date timestamp;

}
