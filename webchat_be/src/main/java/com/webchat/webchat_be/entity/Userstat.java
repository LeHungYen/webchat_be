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
@Table(name = "userstat")
public class Userstat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "statId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer statId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "postCount")
    private Integer postCount;

    @Column(name = "friendCount")
    private Integer friendCount;

    @Column(name = "followerCount")
    private Integer followerCount;

    @Column(name = "followingCount")
    private Integer followingCount;

    @Column(name = "lastUpdate")
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;


}
