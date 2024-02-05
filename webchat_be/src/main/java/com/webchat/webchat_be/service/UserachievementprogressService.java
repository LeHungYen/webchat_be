package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserachievementprogressDTO;
import com.webchat.webchat_be.entity.Userachievementprogress;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserachievementprogressRepository;
import com.webchat.webchat_be.vo.UserachievementprogressQueryVO;
import com.webchat.webchat_be.vo.UserachievementprogressUpdateVO;
import com.webchat.webchat_be.vo.UserachievementprogressVO;

import java.util.NoSuchElementException;

@Service
public class UserachievementprogressService {

    @Autowired
    private UserachievementprogressRepository userachievementprogressRepository;

    public Integer save(UserachievementprogressVO vO) {
        Userachievementprogress bean = new Userachievementprogress();
        BeanUtils.copyProperties(vO, bean);
        bean = userachievementprogressRepository.save(bean);
        return bean.getAchievementProgressId();
    }

    public void delete(Integer id) {
        userachievementprogressRepository.deleteById(id);
    }

    public void update(Integer id, UserachievementprogressUpdateVO vO) {
        Userachievementprogress bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userachievementprogressRepository.save(bean);
    }

    public UserachievementprogressDTO getById(Integer id) {
        Userachievementprogress original = requireOne(id);
        return toDTO(original);
    }

    public Page<UserachievementprogressDTO> query(UserachievementprogressQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserachievementprogressDTO toDTO(Userachievementprogress original) {
        UserachievementprogressDTO bean = new UserachievementprogressDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Userachievementprogress requireOne(Integer id) {
        return userachievementprogressRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
