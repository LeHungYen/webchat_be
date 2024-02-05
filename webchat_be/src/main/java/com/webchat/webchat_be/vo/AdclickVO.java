package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class AdclickVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "clickId can not null")
    private Integer clickId;

    @NotNull(message = "adId can not null")
    private Integer adId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private Date clickTime;

}
