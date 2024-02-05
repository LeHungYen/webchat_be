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
@Table(name = "friendship")
public class Friendship implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "friendshipId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer friendshipId;

    @Column(name = "userId1", insertable=false, updatable=false)
    private Integer userId1;

    @Column(name = "userId2", insertable=false, updatable=false)
    private Integer userId2;

    @Column(name = "status")
    private String status;

    @Column(name = "createAt")
    private Date createAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "userId1" , referencedColumnName = "userId")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "userId2" , referencedColumnName = "userId")
    private User user2;

}
