package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.NotificationDTO;
import com.webchat.webchat_be.entity.Notification;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.NotificationRepository;
import com.webchat.webchat_be.vo.NotificationQueryVO;
import com.webchat.webchat_be.vo.NotificationUpdateVO;
import com.webchat.webchat_be.vo.NotificationVO;

import java.util.NoSuchElementException;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Integer save(NotificationVO vO) {
        Notification bean = new Notification();
        BeanUtils.copyProperties(vO, bean);
        bean = notificationRepository.save(bean);
        return bean.getNotificationId();
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
