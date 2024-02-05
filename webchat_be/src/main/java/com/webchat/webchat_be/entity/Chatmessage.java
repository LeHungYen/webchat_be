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
@Table(name = "chatmessage")
public class Chatmessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "chatMessageId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatMessageId;

    @Column(name = "chatId", insertable=false, updatable=false)
    private Integer chatId;

    @Column(name = "senderUserId", insertable=false, updatable=false)
    private Integer senderUserId;

    @Column(name = "content")
    private String content;

    @Column(name = "mediaType")
    private String mediaType;

    @Column(name = "mediaURL")
    private String mediaURL;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn (name = "chatId")
    private Chat chat;

    @ManyToOne
    @JoinColumn (name = "senderUserId")
    private User user;

}
