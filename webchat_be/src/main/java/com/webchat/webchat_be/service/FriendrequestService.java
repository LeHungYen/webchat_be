package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.FriendrequestDTO;
import com.webchat.webchat_be.entity.Friendrequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public Integer save(FriendrequestVO vO) {
        Friendrequest bean = new Friendrequest();
        BeanUtils.copyProperties(vO, bean);
        bean.setCreatedAt(new Date());
        bean = friendrequestRepository.save(bean);
        return bean.getRequestId();
    }

    public void delete(Integer id) {
        friendrequestRepository.deleteById(id);
    }

    public void update(Integer id, FriendrequestUpdateVO vO) {
        Friendrequest bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        friendrequestRepository.save(bean);
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
