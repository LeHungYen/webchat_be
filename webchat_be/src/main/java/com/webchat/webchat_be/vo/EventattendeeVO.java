package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class EventattendeeVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "attendeeId can not null")
    private Integer attendeeId;

    @NotNull(message = "eventId can not null")
    private Integer eventId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String rsvpStatus;

    private Date createdAt;

}
