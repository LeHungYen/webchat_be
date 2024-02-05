package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class RatingVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "ratingId can not null")
    private Integer ratingId;

    @NotNull(message = "ratedUserId can not null")
    private Integer ratedUserId;

    @NotNull(message = "ratingUserId can not null")
    private Integer ratingUserId;

    private Integer score;

    private String review;

    private Integer isAnonymous;

    private Date createdAt;

}
