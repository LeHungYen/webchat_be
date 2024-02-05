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
@Table(name = "poll")
public class Poll implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pollId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pollId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "question")
    private String question;

    @Column(name = "options")
    private String options;

    @Column(name = "createdAt")
    private Date createdAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "poll")
    private List<Pollvote> pollVotes;
}
