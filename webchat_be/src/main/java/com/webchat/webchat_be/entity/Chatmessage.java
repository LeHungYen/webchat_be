package com.webchat.webchat_be.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
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
@Table(name = "chatmessage")
public class Chatmessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "chatMessageId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatMessageId;

    @Column(name = "replyToMessageId")
    private Integer replyToMessageId;

    @Column(name = "chatId")
    private Integer chatId;

    @Column(name = "chatParticipantId")
    private Integer chatParticipantId;

    @Column(name = "content")
    private String content;

    @Column(name = "mediaType")
    private String mediaType;

    @Column(name = "mediaURL")
    private String mediaURL;

    @Column(name = "createdAt")
    private Date createdAt;

//    @Column(name = "status")
//    private String status;

    @ManyToOne
    @JoinColumn (name = "chatId" , insertable=false, updatable=false)
    private Chat chat;

    @ManyToOne
    @JoinColumn (name = "chatParticipantId" , insertable=false, updatable=false)
    private ChatParticipant chatParticipant;

    @OneToMany (mappedBy = "chatmessage")
    private List<ChatMessageParticipant> chatMessageParticipants;


}
