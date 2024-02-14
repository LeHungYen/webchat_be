package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FriendshipRepository extends JpaRepository<Friendship, Integer>, JpaSpecificationExecutor<Friendship> {
    Friendship findByUserId1AndUserId2(int userId1 , int userId2);
}