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
@Table(name = "userfollowing")
public class UserFollowing implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "followingId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followingId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "followingUserId")
    private Integer followingUserId;

    @Column(name = "followDate")
    private Date followDate;

    @ManyToOne
    @JoinColumn(name = "userId" , insertable=false, updatable=false)
    private  User user;

    @ManyToOne
    @JoinColumn (name = "followingUserId" , insertable=false, updatable=false)
    private  User followingUser;

}
