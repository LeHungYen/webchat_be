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
@Table(name = "report")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "reportId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;

    @Column(name = "reporterUserId", insertable=false, updatable=false)
    private Integer reporterUserId;

    @Column(name = "reportedUserId", insertable=false, updatable=false)
    private Integer reportedUserId;

    @Column(name = "postId", insertable=false, updatable=false)
    private Integer postId;

    @Column(name = "commentId" , insertable=false, updatable=false)
    private Integer commentId;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "reporterUserId" , referencedColumnName = "userId")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "reportedUserId" , referencedColumnName = "userId")
    private User reportedUser;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "commentId")
    private Comment comment;
}
