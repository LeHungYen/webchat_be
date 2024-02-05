package com.webchat.webchat_be.entity;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "groupmember")
public class Groupmember implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "groupMemberId", nullable = false)
    private Integer groupMemberId;

    @Column(name = "groupId", insertable=false, updatable=false)
    private Integer groupId;

    @Column(name = "userId", insertable=false, updatable=false)
    private Integer userId;

    @Column(name = "joinDate")
    private Date joinDate;

    @Column(name = "role")
    private String role;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
