package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class EventDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer eventId;

    private Integer userId;

    private String name;

    private String description;

    private Date startTime;

    private Date endTime;

    private String location;

    private Date createdAt;

}
