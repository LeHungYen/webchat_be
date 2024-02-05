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
@Table(name = "useralbum")
public class Useralbum implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "albumId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer albumId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "albumName")
    private String albumName;

    @Column(name = "description")
    private String description;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn (name = "userId")
    private User user;

    @OneToMany (mappedBy = "userAlbumr")
    private List<Userphoto> userPhotos;

}
