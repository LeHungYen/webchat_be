package com.webchat.webchat_be.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "chat")
public class Chat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "chatId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatId;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "emoji")
    private String emoji;

    @Column(name = "createdAt")
    private Date createdAt;

    @OneToMany (mappedBy = "chat")
    private List<Chatmessage> chatMessages;

    @OneToMany (mappedBy = "chat")
    private List<ChatParticipant> chatParticipants;

    @OneToMany (mappedBy = "chat")
    private List<User> users;

}
