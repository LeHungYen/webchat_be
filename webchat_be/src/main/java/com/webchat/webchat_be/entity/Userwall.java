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

@Data /// entity này có vấn đề (chỉ có postId) , quan hệ one to one to user
@Entity
@Table(name = "userwall")
public class Userwall implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "wallId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wallId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "postId", insertable=false, updatable=false)
    private Integer postId;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private  Post post;

}
