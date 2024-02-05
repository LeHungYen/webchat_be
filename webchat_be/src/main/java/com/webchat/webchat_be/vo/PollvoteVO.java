package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class PollvoteVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "voteId can not null")
    private Integer voteId;

    @NotNull(message = "pollId can not null")
    private Integer pollId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private Integer votedOption;

    private Date createdAt;

}
