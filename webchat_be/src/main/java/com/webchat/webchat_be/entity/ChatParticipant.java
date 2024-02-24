package com.webchat.webchat_be.entity;

import jakarta.persistence.FetchType;
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
@Table(name = "chatparticipant")
public class ChatParticipant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "chatParticipantId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatParticipantId;

    @Column(name = "chatId")
    private Integer chatId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "lastMessageSentAt")
    private Date lastMessageSentAt;

    @Column(name = "joinedAt")
    private Date joinedAt;

    @ManyToOne
    @JoinColumn (name = "userId" , insertable=false, updatable=false)
    private User user;

    @ManyToOne
    @JoinColumn (name = "chatId" , insertable=false, updatable=false)
    private Chat chat;

    @OneToMany(mappedBy = "chatParticipant")
    private List<Chatmessage> chatMessages;

    @OneToMany (mappedBy = "chatParticipant")
    private List<ChatMessageParticipant> chatMessageParticipants;


}
