package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.ChatMessageParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ChatMessageParticipantRepository extends JpaRepository<ChatMessageParticipant, Integer>, JpaSpecificationExecutor<ChatMessageParticipant> {
    List<ChatMessageParticipant> getByChatMessageIdAndChatParticipantId (int chatMessageId , int chatParticipantId);

    List<ChatMessageParticipant> getByChatParticipantIdAndStatusAndChatmessageChatId (int chatParticipantId , String status , int chatId);

    List<ChatMessageParticipant> getByChatParticipantIdAndChatmessageChatIdAndLastViewedAtIsNotNull (int chatParticipantId , int chatId);

    List<ChatMessageParticipant> getAllByChatMessageId (int chatMessageId);

    ChatMessageParticipant getTopByChatParticipantIdOrderByChatMessageIdDesc (int chatParticipantId);
}
