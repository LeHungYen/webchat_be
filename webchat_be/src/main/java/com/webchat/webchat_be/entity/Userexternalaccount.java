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

@Data
@Entity
@Table(name = "userexternalaccount")
public class Userexternalaccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "externalAccountId", nullable = false)
    private Integer externalAccountId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "provider")
    private String provider;

    @Column(name = "externalUserId")
    private String externalUserId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;

}
