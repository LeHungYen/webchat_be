package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.CommentDTO;
import com.webchat.webchat_be.entity.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.CommentRepository;
import com.webchat.webchat_be.vo.CommentQueryVO;
import com.webchat.webchat_be.vo.CommentUpdateVO;
import com.webchat.webchat_be.vo.CommentVO;

import java.util.NoSuchElementException;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Integer save(CommentVO vO) {
        Comment bean = new Comment();
        BeanUtils.copyProperties(vO, bean);
        bean = commentRepository.save(bean);
        return bean.getCommentId();
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

    public void update(Integer id, CommentUpdateVO vO) {
        Comment bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        commentRepository.save(bean);
    }

    public CommentDTO getById(Integer id) {
        Comment original = requireOne(id);
        return toDTO(original);
    }

    public Page<CommentDTO> query(CommentQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CommentDTO toDTO(Comment original) {
        CommentDTO bean = new CommentDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Comment requireOne(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
