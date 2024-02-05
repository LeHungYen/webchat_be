package com.webchat.webchat_be.entity;

import jakarta.persistence.Entity;
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
@Table(name = "adclick")
public class Adclick implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "clickId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clickId;

    @Column(name = "adId", insertable=false, updatable=false)
    private Integer adId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "clickTime")
    private Date clickTime;

    @ManyToOne
    @JoinColumn(name = "adId")
    private Advertisement advertisement;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
