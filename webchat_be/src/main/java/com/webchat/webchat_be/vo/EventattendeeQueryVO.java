package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EventattendeeQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer attendeeId;

    private Integer eventId;

    private Integer userId;

    private String rsvpStatus;

    private Date createdAt;

}
