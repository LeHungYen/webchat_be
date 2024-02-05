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
@Table(name = "userphoto")
public class Userphoto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "photoId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer photoId;

    @Column(name = "albumId",insertable=false, updatable=false)
    private Integer albumId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "photoURL")
    private String photoURL;

    @Column(name = "caption")
    private String caption;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private  Useralbum userAlbumr;

}
