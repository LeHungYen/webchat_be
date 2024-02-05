package com.webchat.webchat_be.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "advertisement")
public class Advertisement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "adId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adId;

    @Column(name = "advertiserUserId", insertable=false, updatable=false)
    private Integer advertiserUserId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "imageURL")
    private String imageURL;

    @Column(name = "link")
    private String link;

    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "advertiserUserId")
    private User user;

    @OneToMany (mappedBy = "advertisement")
    private List<Adclick> adclicks;



}
