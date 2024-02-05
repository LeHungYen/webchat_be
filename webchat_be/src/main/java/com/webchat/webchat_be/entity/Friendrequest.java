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
@Table(name = "friendrequest")
public class Friendrequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "requestId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(name = "senderUserId", insertable=false, updatable=false)
    private Integer senderUserId;

    @Column(name = "receiverUserId", insertable=false, updatable=false)
    private Integer receiverUserId;

    @Column(name = "status")
    private String status;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "senderUserId" , referencedColumnName = "userId")
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "receiverUserId" , referencedColumnName = "userId")
    private User receiverUser;

}
