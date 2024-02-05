package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdclickDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer clickId;

    private Integer adId;

    private Integer userId;

    private Date clickTime;

}
