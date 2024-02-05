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
@Table(name = "userfavorite")
public class Userfavorite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "favoriteId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer favoriteId;

    @Column(name = "userId",insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "targetId", insertable=false, updatable=false)
    private Integer targetId;

    @Column(name = "favoriteType")
    private String favoriteType;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;

    @ManyToOne
    @JoinColumn (name = "targetId")
    private  Post post;

}
