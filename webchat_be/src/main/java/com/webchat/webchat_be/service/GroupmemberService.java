package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.GroupmemberDTO;
import com.webchat.webchat_be.entity.Groupmember;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.GroupmemberRepository;
import com.webchat.webchat_be.vo.GroupmemberQueryVO;
import com.webchat.webchat_be.vo.GroupmemberUpdateVO;
import com.webchat.webchat_be.vo.GroupmemberVO;

import java.util.NoSuchElementException;

@Service
public class GroupmemberService {

    @Autowired
    private GroupmemberRepository groupmemberRepository;

    public Integer save(GroupmemberVO vO) {
        Groupmember bean = new Groupmember();
        BeanUtils.copyProperties(vO, bean);
        bean = groupmemberRepository.save(bean);
        return bean.getGroupMemberId();
    }

    public void delete(Integer id) {
        groupmemberRepository.deleteById(id);
    }

    public void update(Integer id, GroupmemberUpdateVO vO) {
        Groupmember bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        groupmemberRepository.save(bean);
    }

    public GroupmemberDTO getById(Integer id) {
        Groupmember original = requireOne(id);
        return toDTO(original);
    }

    public Page<GroupmemberDTO> query(GroupmemberQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private GroupmemberDTO toDTO(Groupmember original) {
        GroupmemberDTO bean = new GroupmemberDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Groupmember requireOne(Integer id) {
        return groupmemberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
