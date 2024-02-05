package com.webchat.webchat_be.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "userdevicetoken")
public class Userdevicetoken implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "deviceTokenId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deviceTokenId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "deviceToken")
    private String deviceToken;

    @Column(name = "deviceType")
    private String deviceType;

    @Column(name = "timestamp")
    private Date timestamp;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;
}
