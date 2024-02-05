package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class RatingDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer ratingId;

    private Integer ratedUserId;

    private Integer ratingUserId;

    private Integer score;

    private String review;

    private Integer isAnonymous;

    private Date createdAt;

}
