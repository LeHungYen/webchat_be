package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Integer>, JpaSpecificationExecutor<Chat> {
    @Query("SELECT c FROM Chat c JOIN c.chatParticipants cp1 JOIN c.chatParticipants cp2 " +
            "WHERE c.type = :type AND cp1.userId = :userId1 AND cp2.userId = :userId2")
    Optional<Chat> findByTypeAndUserIds(@Param("type") String type, @Param("userId1") int userId1, @Param("userId2") int userId2);
}