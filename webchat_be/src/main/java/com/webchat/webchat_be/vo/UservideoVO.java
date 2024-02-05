package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UservideoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "videoId can not null")
    private Integer videoId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String videoURL;

    private String caption;

    private Date createdAt;

}
