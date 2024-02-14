package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.NotificationDTO;
import com.webchat.webchat_be.dto.UserDTO;
import com.webchat.webchat_be.entity.Notification;
import com.webchat.webchat_be.entity.User;
import com.webchat.webchat_be.repository.UserRepository;
import com.webchat.webchat_be.utilities.Utilities;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.NotificationRepository;
import com.webchat.webchat_be.vo.NotificationQueryVO;
import com.webchat.webchat_be.vo.NotificationUpdateVO;
import com.webchat.webchat_be.vo.NotificationVO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserService userService;
    public NotificationDTO save(NotificationVO vO) {
        Notification bean = new Notification();
        BeanUtils.copyProperties(vO, bean);
        bean.setCreatedAt(new Date());
        bean = notificationRepository.save(bean);

        NotificationDTO notificationDTO = toDTO(bean);
        UserDTO userDTO = userService.getById(notificationDTO.getSenderId());
        notificationDTO.setSender(userDTO);

        return notificationDTO;
    }

    public void delete(Integer id) {
        notificationRepository.deleteById(id);
    }

    public void update(Integer id, NotificationUpdateVO vO) {
        Notification bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        notificationRepository.save(bean);
    }

    public NotificationDTO getById(Integer id) {
        Notification original = requireOne(id);
        return toDTO(original);
    }

    public List<NotificationDTO> getNotifications(){
        UserDTO userDTO = Utilities.getUserDTOFromContext();
        List<Notification> notificationList =  notificationRepository.getByReceiverIdOrderByCreatedAtDesc(userDTO.getUserId());
        List<NotificationDTO> notificationDTOList = notificationList.stream()
                .map(notification -> {
                    NotificationDTO notificationDTO = new NotificationDTO();
                    BeanUtils.copyProperties(notification , notificationDTO);

                    UserDTO sender = new UserDTO();
                    BeanUtils.copyProperties(notification.getSender(), sender);
                    notificationDTO.setSender(sender);

                    return notificationDTO;
                })
                .collect(Collectors.toList());

        return notificationDTOList;
    }

    public Page<NotificationDTO> query(NotificationQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private NotificationDTO toDTO(Notification original) {
        NotificationDTO bean = new NotificationDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Notification requireOne(Integer id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
