package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.PostDTO;
import com.webchat.webchat_be.entity.Post;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.PostRepository;
import com.webchat.webchat_be.vo.PostQueryVO;
import com.webchat.webchat_be.vo.PostUpdateVO;
import com.webchat.webchat_be.vo.PostVO;

import java.util.NoSuchElementException;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Integer save(PostVO vO) {
        Post bean = new Post();
        BeanUtils.copyProperties(vO, bean);
        bean = postRepository.save(bean);
        return bean.getPostId();
    }

    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    public void update(Integer id, PostUpdateVO vO) {
        Post bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        postRepository.save(bean);
    }

    public PostDTO getById(Integer id) {
        Post original = requireOne(id);
        return toDTO(original);
    }

    public Page<PostDTO> query(PostQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private PostDTO toDTO(Post original) {
        PostDTO bean = new PostDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Post requireOne(Integer id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
