package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SuercertificateQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer certificateId;

    private Integer userId;

    private String certificateName;

    private String issuer;

    private Date issueDate;

}
