package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PollvoteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer voteId;

    private Integer pollId;

    private Integer userId;

    private Integer votedOption;

    private Date createdAt;

}
