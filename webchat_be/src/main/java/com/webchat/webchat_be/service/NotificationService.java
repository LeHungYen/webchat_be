package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.NotificationDTO;
import com.webchat.webchat_be.dto.UserDTO;
import com.webchat.webchat_be.entity.Notification;
import com.webchat.webchat_be.entity.User;
import com.webchat.webchat_be.enums.NotificationType;
import com.webchat.webchat_be.repository.UserRepository;
import com.webchat.webchat_be.utilities.Utilities;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private UserService userService;
    public NotificationDTO save(NotificationVO vO) {

        // check notification already existed when type = send friend request
        NotificationType notificationType = NotificationType.valueOf(vO.getType());

        if(notificationType.equals(NotificationType.SEND_FRIEND_REQUEST)){
            NotificationDTO notificationDTO = getBySenderIdAndReceiverIdAndType(vO.getSenderId() , vO.getReceiverId() , vO.getType());
            if(notificationDTO.getNotificationId() != null){
                return notificationDTO;
            }
        }

        if(notificationType.equals(NotificationType.SEND_FRIEND_REQUEST)){
            NotificationDTO notificationDTO = getBySenderIdAndReceiverIdAndType(vO.getReceiverId() , vO.getSenderId() , vO.getType());
            if(notificationDTO.getNotificationId() != null){
                return notificationDTO;
            }
        }
        // end


        Notification bean = new Notification();
        BeanUtils.copyProperties(vO, bean);
        bean.setCreatedAt(new Date());
        bean.setType(String.valueOf(NotificationType.SEND_FRIEND_REQUEST));
        bean = notificationRepository.save(bean);

        NotificationDTO notificationDTO = toDTO(bean);
        UserDTO userDTO = userService.getById(notificationDTO.getSenderId());
        notificationDTO.setSender(userDTO);

        simpMessagingTemplate.convertAndSend("/topic/notification" , notificationDTO);
        return notificationDTO;
    }

    public NotificationDTO getBySenderIdAndReceiverIdAndType (int senderId , int receiverId , String type){
        Notification notification = notificationRepository.getBySenderIdAndReceiverIdAndType(senderId,receiverId,type);
        if(notification != null){
           return toDTO(notification);
        }

        return new NotificationDTO();
    }

    public void delete(Integer id) {
        notificationRepository.deleteById(id);
    }

    public void deleteAllByTypeSendFriendRequest (int userId1 , int userId2) {
        NotificationDTO notificationDTO = getBySenderIdAndReceiverIdAndType(userId1 , userId2 , String.valueOf(NotificationType.SEND_FRIEND_REQUEST));

        if(notificationDTO.getNotificationId() != null){
            delete(notificationDTO.getNotificationId());
        }

        notificationDTO = getBySenderIdAndReceiverIdAndType (userId2 , userId1 , String.valueOf(NotificationType.SEND_FRIEND_REQUEST));
        if(notificationDTO.getNotificationId() != null){
            delete(notificationDTO.getNotificationId());
        }

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
