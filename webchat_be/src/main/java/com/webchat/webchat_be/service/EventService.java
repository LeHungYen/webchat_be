package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.EventDTO;
import com.webchat.webchat_be.entity.Event;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.EventRepository;
import com.webchat.webchat_be.vo.EventQueryVO;
import com.webchat.webchat_be.vo.EventUpdateVO;
import com.webchat.webchat_be.vo.EventVO;

import java.util.NoSuchElementException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Integer save(EventVO vO) {
        Event bean = new Event();
        BeanUtils.copyProperties(vO, bean);
        bean = eventRepository.save(bean);
        return bean.getEventId();
    }

    public void delete(Integer id) {
        eventRepository.deleteById(id);
    }

    public void update(Integer id, EventUpdateVO vO) {
        Event bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        eventRepository.save(bean);
    }

    public EventDTO getById(Integer id) {
        Event original = requireOne(id);
        return toDTO(original);
    }

    public Page<EventDTO> query(EventQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private EventDTO toDTO(Event original) {
        EventDTO bean = new EventDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Event requireOne(Integer id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
