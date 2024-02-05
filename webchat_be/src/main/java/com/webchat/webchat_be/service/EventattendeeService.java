package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.EventattendeeDTO;
import com.webchat.webchat_be.entity.Eventattendee;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.EventattendeeRepository;
import com.webchat.webchat_be.vo.EventattendeeQueryVO;
import com.webchat.webchat_be.vo.EventattendeeUpdateVO;
import com.webchat.webchat_be.vo.EventattendeeVO;

import java.util.NoSuchElementException;

@Service
public class EventattendeeService {

    @Autowired
    private EventattendeeRepository eventattendeeRepository;

    public Integer save(EventattendeeVO vO) {
        Eventattendee bean = new Eventattendee();
        BeanUtils.copyProperties(vO, bean);
        bean = eventattendeeRepository.save(bean);
        return bean.getAttendeeId();
    }

    public void delete(Integer id) {
        eventattendeeRepository.deleteById(id);
    }

    public void update(Integer id, EventattendeeUpdateVO vO) {
        Eventattendee bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        eventattendeeRepository.save(bean);
    }

    public EventattendeeDTO getById(Integer id) {
        Eventattendee original = requireOne(id);
        return toDTO(original);
    }

    public Page<EventattendeeDTO> query(EventattendeeQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private EventattendeeDTO toDTO(Eventattendee original) {
        EventattendeeDTO bean = new EventattendeeDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Eventattendee requireOne(Integer id) {
        return eventattendeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
