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
import java.util.List;

@Data
@Entity
@Table(name = "group")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "groupId", nullable = false)
    private Integer groupId;

    @Column(name = "groupName")
    private String groupName;

    @Column(name = "description")
    private String description;

    @Column(name = "adminUserId", insertable=false, updatable=false)
    private Integer adminUserId;

    @Column(name = "createAt")
    private Date createAt;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "adminUserId")
    private User user;

    @OneToMany (mappedBy = "group")
    List<Groupmember> groupMembers;

}
