package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class EventVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "eventId can not null")
    private Integer eventId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    @NotNull(message = "name can not null")
    private String name;

    private String description;

    private Date startTime;

    private Date endTime;

    private String location;

    private Date createdAt;

}
