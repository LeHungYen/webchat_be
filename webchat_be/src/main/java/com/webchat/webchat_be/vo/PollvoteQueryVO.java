package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PollvoteQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer voteId;

    private Integer pollId;

    private Integer userId;

    private Integer votedOption;

    private Date createdAt;

}
