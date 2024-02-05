package com.webchat.webchat_be.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


@Data
public class SuercertificateVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "certificateId can not null")
    private Integer certificateId;

    @NotNull(message = "userId can not null")
    private Integer userId;

    private String certificateName;

    private String issuer;

    private Date issueDate;

}
