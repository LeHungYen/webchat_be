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
@Table(name = "rating")
public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ratingId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ratingId;

    @Column(name = "ratedUserId", insertable=false, updatable=false)
    private Integer ratedUserId;

    @Column(name = "ratingUserId", insertable=false, updatable=false)
    private Integer ratingUserId;

    @Column(name = "score")
    private Integer score;

    @Column(name = "review")
    private String review;

    @Column(name = "isAnonymous")
    private Integer isAnonymous;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "ratedUserId" , referencedColumnName = "userId")
    private User ratedUser;

    @ManyToOne
    @JoinColumn(name = "ratingUserId" , referencedColumnName = "userId")
    private User ratingUser;

}
