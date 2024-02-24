package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.ChatDTO;
import com.webchat.webchat_be.dto.ChatParticipantDTO;
import com.webchat.webchat_be.dto.ChatmessageDTO;
import com.webchat.webchat_be.dto.NotificationDTO;
import com.webchat.webchat_be.entity.ChatParticipant;
import com.webchat.webchat_be.entity.Chatmessage;
import com.webchat.webchat_be.enums.ChatMessageMediaType;
import com.webchat.webchat_be.enums.ChatMessageStatus;
import com.webchat.webchat_be.repository.ChatParticipantRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.ChatmessageRepository;
import com.webchat.webchat_be.vo.ChatmessageQueryVO;
import com.webchat.webchat_be.vo.ChatmessageUpdateVO;
import com.webchat.webchat_be.vo.ChatmessageVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class ChatmessageService {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private ChatmessageRepository chatmessageRepository;
    @Autowired
    private ChatParticipantRepository chatParticipantRepository;
    @Autowired
    private ChatParticipantService chatParticipantService;

    public ChatmessageDTO save(ChatmessageVO vO) {
        Chatmessage bean = new Chatmessage();
        BeanUtils.copyProperties(vO, bean);
        bean.setCreatedAt(new Date());
        bean = chatmessageRepository.save(bean);


        // update chat paticipant before save message
        List<ChatParticipant> chatParticipants = chatParticipantService.findByChatId(vO.getChatId());
        for (ChatParticipant  chatParticipant: chatParticipants) {
            chatParticipant.setLastMessageSentAt(new Date());
            chatParticipantRepository.save(chatParticipant);

            // send message to chat paticipant
            simpMessagingTemplate.convertAndSend("/topic/chatMessage/"+ chatParticipant.getUserId() , bean);
        }
        return toDTO(bean);
    }

    public ChatmessageDTO saveImg(int chatId , int chatParticipantId , MultipartFile file) throws IOException {
        // update chat paticipant before save message
        ChatParticipant chatParticipant = chatParticipantRepository.findById(chatParticipantId)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + chatParticipantId));;
        chatParticipant.setLastMessageSentAt(new Date());
        chatParticipantRepository.save(chatParticipant);

        Chatmessage bean = new Chatmessage();
        bean.setChatMessageId(null);
        bean.setChatId(chatId);
        bean.setChatParticipantId(chatParticipantId);
        bean.setReplyToMessageId(null);
        bean.setContent("");
        bean.setMediaType(String.valueOf(ChatMessageMediaType.IMAGE));
        bean.setMediaURL(saveFile(file));
        bean.setCreatedAt(new Date());
        bean.setStatus(String.valueOf(ChatMessageStatus.SENDED));
        bean = chatmessageRepository.save(bean);

        // send message to subcribers
        simpMessagingTemplate.convertAndSend("/topic/chatMessage/"+bean.getChatId() , bean);

        return null;
    }

    private String saveFile(MultipartFile file) throws IOException {
        String directoryPath = "/Users/lehungyen/Desktop/chatSphereFe/webchatFE/src/assets/imgs";

        // Create the directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Normalize the file name
        String originalFileName = file.getOriginalFilename();
        String normalizedFileName = normalizeFileName(originalFileName);

        // Save the file to the directory
        String filePath = directoryPath + File.separator + normalizedFileName;
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }

        return normalizedFileName;
    }

    private String normalizeFileName(String fileName) {
        // Replace spaces with underscores
        fileName = fileName.replaceAll("\\s+", "_");

        // Remove non-alphanumeric characters
        fileName = fileName.replaceAll("[^a-zA-Z0-9._]", "");

        // Convert Vietnamese accented characters to their unaccented equivalents
        fileName = java.text.Normalizer.normalize(fileName, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        return fileName;
    }


    public void delete(Integer id) {
        chatmessageRepository.deleteById(id);
    }

    public void update(Integer id, ChatmessageUpdateVO vO) {
        Chatmessage bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        chatmessageRepository.save(bean);
    }

    public ChatmessageDTO getById(Integer id) {
        Chatmessage original = requireOne(id);
        return toDTO(original);
    }

    public Page<ChatmessageDTO> query(ChatmessageQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ChatmessageDTO toDTO(Chatmessage original) {
        ChatmessageDTO bean = new ChatmessageDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Chatmessage requireOne(Integer id) {
        return chatmessageRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public ChatmessageDTO getLatestChatMessageByChatId(int chatId) {
        Chatmessage chatmessage = chatmessageRepository.findTopByChatIdOrderByCreatedAtDesc(chatId);
        if(chatmessage != null){
            return toDTO(chatmessage);
        }
        return new ChatmessageDTO();
    }

    public Page<ChatmessageDTO> findAllByChatId (int chatId , int pageNumber){
        // set status trước khi lấy


        Sort sort = Sort.by("createdAt").descending();
        Pageable pageable = PageRequest.of(pageNumber , 20 , sort);

        Page<Chatmessage> chatmessagePage = chatmessageRepository.findAllByChatId(chatId, pageable);

        Page<ChatmessageDTO> chatmessageDTOPage = chatmessagePage.map(chatmessage -> {
            ChatmessageDTO dto = new ChatmessageDTO();
            BeanUtils.copyProperties(chatmessage , dto);
            return dto;
        });

        return chatmessageDTOPage;
    }
}
