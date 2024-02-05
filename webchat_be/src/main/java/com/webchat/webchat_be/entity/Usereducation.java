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
@Table(name = "usereducation")
public class Usereducation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "educationId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer educationId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "schoolName")
    private String schoolName;

    @Column(name = "degree")
    private String degree;

    @Column(name = "fieldOfStudy")
    private String fieldOfStudy;

    @Column(name = "graduationYear")
    private Integer graduationYear;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;

}
