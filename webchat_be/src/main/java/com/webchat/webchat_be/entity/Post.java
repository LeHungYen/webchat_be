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
@Table(name = "post")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "postId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "content")
    private String content;

    @Column(name = "mediaType")
    private String mediaType;

    @Column(name = "mediaURL")
    private String mediaURL;

    @Column(name = "tag")
    private String tag;

    @Column(name = "location")
    private String location;

    @Column(name = "privacy")
    private String privacy;

    @Column(name = "createAt")
    private Date createAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "post")
    private List<Userfavorite> userFavorites;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
