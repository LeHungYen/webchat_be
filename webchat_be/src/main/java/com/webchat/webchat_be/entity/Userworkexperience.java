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
@Table(name = "userworkexperience")
public class Userworkexperience implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "experienceId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer experienceId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "position")
    private String position;

    @Column(name = "employmentPeriod")
    private String employmentPeriod;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;


}
