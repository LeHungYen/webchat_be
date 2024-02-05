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
@Table(name = "pollvote")
public class Pollvote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "voteId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voteId;

    @Column(name = "pollId", insertable=false, updatable=false)
    private Integer pollId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "votedOption")
    private Integer votedOption;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pollId")
    private Poll poll;

}
