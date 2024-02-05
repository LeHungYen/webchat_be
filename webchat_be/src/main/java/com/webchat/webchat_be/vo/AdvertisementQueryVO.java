package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdvertisementQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer adId;

    private Integer advertiserUserId;

    private String title;

    private String description;

    private String imageURL;

    private String link;

    private Date startTime;

    private Date endTime;

    private Date createdAt;

}
