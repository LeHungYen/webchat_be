package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ChatMessageParticipantDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer chatMessageParticipantId;

    private Integer chatMessageId;

    private Integer chatParticipantId;

    private String status;

    private Date lastViewedAt;

//    private int chatParticipantIdOfSender;

    private String firstName;

    private String lastName;

    private String userProfilePicture;

}
