package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Friendrequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FriendrequestRepository extends JpaRepository<Friendrequest, Integer>, JpaSpecificationExecutor<Friendrequest> {
        Friendrequest findBySenderUserIdAndReceiverUserId(int senderUserId, int receiverUserId);

}