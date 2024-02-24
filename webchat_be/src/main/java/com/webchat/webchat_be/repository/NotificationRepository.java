package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer>, JpaSpecificationExecutor<Notification> {
    List<Notification> getByReceiverIdOrderByCreatedAtDesc (int receiverId);

    Notification getBySenderIdAndReceiverIdAndType (int senderId , int receiverId , String type);
}