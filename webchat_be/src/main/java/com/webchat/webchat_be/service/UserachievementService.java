package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserachievementDTO;
import com.webchat.webchat_be.entity.Userachievement;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserachievementRepository;
import com.webchat.webchat_be.vo.UserachievementQueryVO;
import com.webchat.webchat_be.vo.UserachievementUpdateVO;
import com.webchat.webchat_be.vo.UserachievementVO;

import java.util.NoSuchElementException;

@Service
public class UserachievementService {

    @Autowired
    private UserachievementRepository userachievementRepository;

    public Integer save(UserachievementVO vO) {
        Userachievement bean = new Userachievement();
        BeanUtils.copyProperties(vO, bean);
        bean = userachievementRepository.save(bean);
        return bean.getAchievementId();
    }

    public void delete(Integer id) {
        userachievementRepository.deleteById(id);
    }

    public void update(Integer id, UserachievementUpdateVO vO) {
        Userachievement bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userachievementRepository.save(bean);
    }

    public UserachievementDTO getById(Integer id) {
        Userachievement original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserachievementDTO> query(UserachievementQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserachievementDTO toDTO(Userachievement original) {
        UserachievementDTO bean = new UserachievementDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userachievement requireOne(Integer id) {
        return userachievementRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
