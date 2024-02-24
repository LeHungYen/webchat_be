package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Chatmessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChatmessageRepository extends JpaRepository<Chatmessage, Integer>, JpaSpecificationExecutor<Chatmessage> {
//    Page<Chatmessage> findAllByUserIdAndChatIdIsDistinct (int userId , Pageable pageable);
    Chatmessage findTopByChatIdOrderByCreatedAtDesc(int chatId);

    Page<Chatmessage> findAllByChatId (int chatId , Pageable pageable);
}