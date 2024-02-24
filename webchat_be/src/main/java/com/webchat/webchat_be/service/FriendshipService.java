package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.FriendrequestDTO;
import com.webchat.webchat_be.dto.FriendshipDTO;
import com.webchat.webchat_be.dto.UserfollowingDTO;
import com.webchat.webchat_be.entity.Friendship;
import com.webchat.webchat_be.entity.UserFollowing;
import com.webchat.webchat_be.enums.FriendRequestStatus;
import com.webchat.webchat_be.repository.UserfollowingRepository;
import com.webchat.webchat_be.vo.UserfollowingVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.FriendshipRepository;
import com.webchat.webchat_be.vo.FriendshipQueryVO;
import com.webchat.webchat_be.vo.FriendshipUpdateVO;
import com.webchat.webchat_be.vo.FriendshipVO;

import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserfollowingRepository userfollowingRepository;

    @Autowired UserfollowingService userfollowingService;

    @Autowired
    FriendrequestService friendrequestService;
    public Integer save(FriendshipVO vO) {
        // delete friend request before be friend
           FriendrequestDTO friendrequestDTO = friendrequestService.getBySenderIdAndReceiveId(vO.getUserId1() , vO.getUserId2());
           friendrequestService.delete(friendrequestDTO.getRequestId());

        // follow before be friend
        FriendRequestStatus friendRequestStatus = FriendRequestStatus.valueOf(friendrequestDTO.getStatus());

        if(friendRequestStatus.equals(FriendRequestStatus.WAITING_FOR_THE_RECEIVER_TO_RESPONSE)){
            UserfollowingVO userfollowingVO = new UserfollowingVO();
            userfollowingVO.setUserId(friendrequestDTO.getReceiverUserId());
            userfollowingVO.setFollowingUserId(friendrequestDTO.getSenderUserId());
            userfollowingService.save(userfollowingVO);
        }else if(friendRequestStatus.equals(FriendRequestStatus.WAITING_FOR_THE_SENDER_TO_RESPONSE)){
            UserfollowingVO userfollowingVO = new UserfollowingVO();
            userfollowingVO.setUserId(friendrequestDTO.getSenderUserId());
            userfollowingVO.setFollowingUserId(friendrequestDTO.getReceiverUserId());
            userfollowingService.save(userfollowingVO);
        }

        // delete notification before be friend

        Friendship bean = new Friendship();
        BeanUtils.copyProperties(vO, bean);
        bean.setCreateAt(new Date());
        bean.setUpdatedAt(new Date());
        bean = friendshipRepository.save(bean);
        return bean.getFriendshipId();
    }

    public void delete(int friendshipId , int userId1 , int userId2) {
        //unfollow before unfriend
        UserFollowing userFollowing = userfollowingRepository.findByUserIdAndFollowingUserId(userId1, userId2);
        if(userFollowing != null){
            userfollowingRepository.deleteById(userFollowing.getFollowingId());
        }

        userFollowing = userfollowingRepository.findByUserIdAndFollowingUserId(userId2,  userId1);
        if(userFollowing != null){
            userfollowingRepository.deleteById(userFollowing.getFollowingId());
        }

        // unfriend
        friendshipRepository.deleteById(friendshipId);
    }

    public void update(Integer id, FriendshipUpdateVO vO) {
        Friendship bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        friendshipRepository.save(bean);
    }

    public FriendshipDTO getById(Integer id) {
        Friendship original = requireOne(id);
        return toDTO(original);
    }

    public Page<FriendshipDTO> query(FriendshipQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FriendshipDTO toDTO(Friendship original) {
        FriendshipDTO bean = new FriendshipDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Friendship requireOne(Integer id) {
        return friendshipRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public FriendshipDTO findByUserId1AndUserId2(int userId1 , int userId2){
        Friendship friendship = friendshipRepository.findByUserId1AndUserId2(userId1 , userId2);
        if(friendship == null){
            friendship = friendshipRepository.findByUserId1AndUserId2(userId2 , userId1);
        }
        if(friendship == null){
            return null;
        }
        return toDTO(friendship);
    }
}
