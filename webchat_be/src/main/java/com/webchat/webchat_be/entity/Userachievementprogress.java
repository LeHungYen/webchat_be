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
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "userachievementprogress")
public class Userachievementprogress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievementProgressId", nullable = false)
    private Integer achievementProgressId;

    @Column(name = "achievementId", insertable=false, updatable=false)
    private Integer achievementId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "progressValue")
    private BigDecimal progressValue;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn (name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "achievementId")
    private Userachievement userAchievement;

}
