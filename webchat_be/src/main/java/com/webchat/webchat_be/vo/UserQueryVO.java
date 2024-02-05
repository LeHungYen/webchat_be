package com.webchat.webchat_be.vo;


import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class UserQueryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String username;

    private String passwordHash;

    private String email;

    private String fullName;

    private Date birthdate;

    private String gender;

    private String profilePicture;

    private String coverPhoto;

    private String bio;

    private String website;

    private String phoneNumber;

    private java.util.Date lastLogin;

    private java.util.Date registrationDate;

    private String status;

    private String role;

}
