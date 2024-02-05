package com.webchat.webchat_be.dto;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SuercertificateDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer certificateId;

    private Integer userId;

    private String certificateName;

    private String issuer;

    private Date issueDate;

}
