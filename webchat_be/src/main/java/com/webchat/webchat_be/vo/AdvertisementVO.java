package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class AdvertisementVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "adId can not null")
    private Integer adId;

    @NotNull(message = "advertiserUserId can not null")
    private Integer advertiserUserId;

    private String title;

    private String description;

    private String imageURL;

    private String link;

    private Date startTime;

    private Date endTime;

    private Date createdAt;

}
