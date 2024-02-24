package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.ChatParticipant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatParticipantRepository extends JpaRepository<ChatParticipant, Integer>{

//    Page<ChatParticipant> findByUserIdOrderByChatMessageCreatedAtDesc(Integer userId, Pageable pageable);

        Page<ChatParticipant> getAllByUserId (int userId , Pageable pageable);

        List<ChatParticipant> getAllByChatId (int chatId);

}