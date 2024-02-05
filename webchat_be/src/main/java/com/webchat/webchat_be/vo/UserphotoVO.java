package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class UserphotoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "photoId can not null")
    private Integer photoId;

    @NotNull(message = "albumId can not null")
    private Integer albumId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String photoURL;

    private String caption;

    private Date createdAt;

}
