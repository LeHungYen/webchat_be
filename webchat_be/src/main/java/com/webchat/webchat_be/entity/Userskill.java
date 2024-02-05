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

@Data
@Entity
@Table(name = "userskill")
public class Userskill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "skillId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skillId;

    @Column(name = "userId",insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "skillName")
    private String skillName;

    @Column(name = "skillLevel")
    private String skillLevel;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;


}
