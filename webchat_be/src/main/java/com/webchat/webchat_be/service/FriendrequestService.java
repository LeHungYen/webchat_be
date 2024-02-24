package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.FriendrequestDTO;
import com.webchat.webchat_be.dto.NotificationDTO;
import com.webchat.webchat_be.entity.Friendrequest;
import com.webchat.webchat_be.enums.FriendRequestStatus;
import com.webchat.webchat_be.enums.NotificationType;
import com.webchat.webchat_be.vo.NotificationVO;
import com.webchat.webchat_be.vo.UserfollowingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.FriendrequestRepository;
import com.webchat.webchat_be.vo.FriendrequestQueryVO;
import com.webchat.webchat_be.vo.FriendrequestUpdateVO;
import com.webchat.webchat_be.vo.FriendrequestVO;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class FriendrequestService {

    @Autowired
    private FriendrequestRepository friendrequestRepository;

    @Autowired
    private UserfollowingService userfollowingService;

    @Autowired NotificationService notificationService;

    public Integer save(FriendrequestVO vO) {
        // follow and send notification before send friend request
        handleFollowAndNotification(vO);

        Friendrequest bean = new Friendrequest();
        BeanUtils.copyProperties(vO, bean);
        bean.setCreatedAt(new Date());
        bean = friendrequestRepository.save(bean);
        return bean.getRequestId();
    }

    public void delete(Integer id) {
        FriendrequestDTO friendrequestDTO = getById(id);
        // delete notification before delete friend request
        notificationService.deleteAllByTypeSendFriendRequest(friendrequestDTO.getReceiverUserId() , friendrequestDTO.getSenderUserId());

        friendrequestRepository.deleteById(id);
    }

    public void update(Integer id, FriendrequestUpdateVO vO) {
        handleFollowAndNotification(vO);

        Friendrequest bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        friendrequestRepository.save(bean);
    }

    public void handleFollowAndNotification(FriendrequestVO vO){
        FriendRequestStatus friendRequestStatus = FriendRequestStatus.valueOf(vO.getStatus());
        if(friendRequestStatus.equals(FriendRequestStatus.WAITING_FOR_THE_RECEIVER_TO_RESPONSE)){
            // follow
            UserfollowingVO userfollowingVO = new UserfollowingVO();
            userfollowingVO.setUserId(vO.getSenderUserId());
            userfollowingVO.setFollowingUserId(vO.getReceiverUserId());
            userfollowingService.save(userfollowingVO);

            // send notification
            NotificationVO notificationVO = new NotificationVO();
            notificationVO.setSenderId(vO.getSenderUserId());
            notificationVO.setReceiverId(vO.getReceiverUserId());
            notificationVO.setType(String.valueOf(NotificationType.SEND_FRIEND_REQUEST));
            notificationVO.setLink("http://localhost:3000/userWall?id=" + vO.getSenderUserId());
            notificationVO.setRead(false);
            notificationService.save(notificationVO);

        }else if(friendRequestStatus.equals(FriendRequestStatus.WAITING_FOR_THE_SENDER_TO_RESPONSE)){
            //follow
            UserfollowingVO userfollowingVO = new UserfollowingVO();
            userfollowingVO.setUserId(vO.getReceiverUserId());
            userfollowingVO.setFollowingUserId(vO.getSenderUserId());
            userfollowingService.save(userfollowingVO);

            // send notification
            NotificationVO notificationVO = new NotificationVO();
            notificationVO.setSenderId(vO.getReceiverUserId());
            notificationVO.setReceiverId(vO.getSenderUserId());
            notificationVO.setType(String.valueOf(NotificationType.SEND_FRIEND_REQUEST));
            notificationVO.setLink("http://localhost:3000/userWall?id=" + vO.getReceiverUserId());
            notificationVO.setRead(false);
            notificationService.save(notificationVO);
        }else if(friendRequestStatus.equals(FriendRequestStatus.THE_RECEIVER_CANCELED)){
            notificationService.deleteAllByTypeSendFriendRequest(vO.getReceiverUserId() , vO.getSenderUserId());
        }else if(friendRequestStatus.equals(FriendRequestStatus.THE_SENDER_CANCELED)){
            notificationService.deleteAllByTypeSendFriendRequest(vO.getReceiverUserId() , vO.getSenderUserId());
        }
    }

    public FriendrequestDTO getById(Integer id) {
        Friendrequest original = requireOne(id);
        return toDTO(original);
    }

    public Page<FriendrequestDTO> query(FriendrequestQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FriendrequestDTO toDTO(Friendrequest original) {
        FriendrequestDTO bean = new FriendrequestDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Friendrequest requireOne(Integer id) {
        return friendrequestRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public FriendrequestDTO getBySenderIdAndReceiveId (int senderId , int receiverId){
        Friendrequest friendrequest = friendrequestRepository.findBySenderUserIdAndReceiverUserId(senderId , receiverId);

        if(friendrequest == null){
            friendrequest = friendrequestRepository.findBySenderUserIdAndReceiverUserId(receiverId , senderId);
        }
        if (friendrequest == null) {
            return null;
        }
        return toDTO(friendrequest);
    }
}
