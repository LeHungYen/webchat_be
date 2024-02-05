package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChatRepository extends JpaRepository<Chat, Integer>, JpaSpecificationExecutor<Chat> {

}