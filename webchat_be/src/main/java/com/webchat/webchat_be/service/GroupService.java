package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.GroupDTO;
import com.webchat.webchat_be.entity.Group;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.GroupRepository;
import com.webchat.webchat_be.vo.GroupQueryVO;
import com.webchat.webchat_be.vo.GroupUpdateVO;
import com.webchat.webchat_be.vo.GroupVO;

import java.util.NoSuchElementException;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Integer save(GroupVO vO) {
        Group bean = new Group();
        BeanUtils.copyProperties(vO, bean);
        bean = groupRepository.save(bean);
        return bean.getGroupId();
    }

    public void delete(Integer id) {
        groupRepository.deleteById(id);
    }

    public void update(Integer id, GroupUpdateVO vO) {
        Group bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        groupRepository.save(bean);
    }

    public GroupDTO getById(Integer id) {
        Group original = requireOne(id);
        return toDTO(original);
    }

    public Page<GroupDTO> query(GroupQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private GroupDTO toDTO(Group original) {
        GroupDTO bean = new GroupDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Group requireOne(Integer id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
