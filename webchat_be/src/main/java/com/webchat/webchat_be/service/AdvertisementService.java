package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.AdvertisementDTO;
import com.webchat.webchat_be.entity.Advertisement;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.AdvertisementRepository;
import com.webchat.webchat_be.vo.AdvertisementQueryVO;
import com.webchat.webchat_be.vo.AdvertisementUpdateVO;
import com.webchat.webchat_be.vo.AdvertisementVO;

import java.util.NoSuchElementException;

@Service
public class AdvertisementService {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    public Integer save(AdvertisementVO vO) {
        Advertisement bean = new Advertisement();
        BeanUtils.copyProperties(vO, bean);
        bean = advertisementRepository.save(bean);
        return bean.getAdId();
    }

    public void delete(Integer id) {
        advertisementRepository.deleteById(id);
    }

    public void update(Integer id, AdvertisementUpdateVO vO) {
        Advertisement bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        advertisementRepository.save(bean);
    }

    public AdvertisementDTO getById(Integer id) {
        Advertisement original = requireOne(id);
        return toDTO(original);
    }

    public Page<AdvertisementDTO> query(AdvertisementQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private AdvertisementDTO toDTO(Advertisement original) {
        AdvertisementDTO bean = new AdvertisementDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Advertisement requireOne(Integer id) {
        return advertisementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
