package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
public class UserlocationhistoryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "locationHistoryId can not null")
    private Integer locationHistoryId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Date timestamp;

}
