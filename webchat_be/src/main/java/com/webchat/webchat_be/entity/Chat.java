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
@Table(name = "chat")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "chatId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;

    @Column(name = "participant1Id", insertable=false, updatable=false)
    private Integer participant1Id;

    @Column(name = "participant2Id", insertable=false, updatable=false)
    private Integer participant2Id;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn (name = "participant1Id" , referencedColumnName = "userId")
    private User participant1;

    @ManyToOne
    @JoinColumn (name = "participant2Id" , referencedColumnName = "userId")
    private User participant2;

    @OneToMany (mappedBy = "chat")
    private List<Chatmessage> chatMessages;

}
