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
@Table(name = "notification")
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "notificationId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;

    @Column(name = "senderId")
    private Integer senderId;

    @Column(name = "receiverId")
    private Integer receiverId;

    @Column(name = "type")
    private String type;

    @Column(name = "link")
    private String link;

    @Column(name = "isRead")
    private boolean isRead;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "senderId" , insertable=false, updatable=false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId" , insertable=false, updatable=false)
    private User receiver;

}
