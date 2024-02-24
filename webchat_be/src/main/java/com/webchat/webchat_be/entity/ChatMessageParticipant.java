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

@Data
@Entity
@Table(name = "ChatMessageParticipant")
public class ChatMessageParticipant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatMessageParticipantId", nullable = false)
    private Integer chatMessageParticipantId;

    @Column(name = "chatMessageId", nullable = false)
    private Integer chatMessageId;

    @Column(name = "chatParticipantId", nullable = false)
    private Integer chatParticipantId;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn (name = "chatMessageId" , insertable = false , updatable = false)
    private  Chatmessage chatmessage;

    @ManyToOne
    @JoinColumn (name = "chatParticipantId" , insertable = false , updatable = false)
    private  ChatParticipant chatParticipant;

}
