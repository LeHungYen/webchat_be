package com.webchat.webchat_be.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "userreaction")
public class Userreaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "reactionId", nullable = false)
    private Integer reactionId;

    @Column(name = "userId" , insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "targetType")
    private String targetType;

    @Column(name = "targetId_FK")
    private Integer targetidFk;

    @Column(name = "reactionType")
    private String reactionType;

    @Column(name = "CreatedAt", nullable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;


}
