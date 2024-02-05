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
@Table(name = "usersearchhistory")
public class Usersearchhistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "searchHistoryId", nullable = false)
    private Integer searchHistoryId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "searchQuery")
    private String searchQuery;

    @Column(name = "searchTimestamp")
    private Date searchTimestamp;

    @ManyToOne
    @JoinColumn(name = "userId")
    private  User user;


}
