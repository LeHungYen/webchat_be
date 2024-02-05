package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.RatingDTO;
import com.webchat.webchat_be.entity.Rating;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.RatingRepository;
import com.webchat.webchat_be.vo.RatingQueryVO;
import com.webchat.webchat_be.vo.RatingUpdateVO;
import com.webchat.webchat_be.vo.RatingVO;

import java.util.NoSuchElementException;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public Integer save(RatingVO vO) {
        Rating bean = new Rating();
        BeanUtils.copyProperties(vO, bean);
        bean = ratingRepository.save(bean);
        return bean.getRatingId();
    }

    public void delete(Integer id) {
        ratingRepository.deleteById(id);
    }

    public void update(Integer id, RatingUpdateVO vO) {
        Rating bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ratingRepository.save(bean);
    }

    public RatingDTO getById(Integer id) {
        Rating original = requireOne(id);
        return toDTO(original);
    }

    public Page<RatingDTO> query(RatingQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private RatingDTO toDTO(Rating original) {
        RatingDTO bean = new RatingDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Rating requireOne(Integer id) {
        return ratingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
