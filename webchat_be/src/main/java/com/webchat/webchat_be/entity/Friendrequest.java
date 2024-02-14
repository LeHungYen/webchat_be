package com.webchat.webchat_be.entity;

import com.webchat.webchat_be.enums.FriendRequestStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "friendrequest")
public class Friendrequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "requestId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(name = "senderUserId")
    private Integer senderUserId;

    @Column(name = "receiverUserId")
    private Integer receiverUserId;

    @Column(name = "status")
//    @Enumerated(EnumType.STRING)
//    private FriendRequestStatus status;
    private String status;

    @Column(name = "createdAt")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "senderUserId" , referencedColumnName = "userId" , insertable=false, updatable=false)
    private User senderUser;

    @ManyToOne
    @JoinColumn(name = "receiverUserId" , referencedColumnName = "userId" , insertable=false, updatable=false)
    private User receiverUser;

}
