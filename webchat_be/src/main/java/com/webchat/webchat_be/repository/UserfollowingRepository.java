package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.UserFollowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserfollowingRepository extends JpaRepository<UserFollowing, Integer>, JpaSpecificationExecutor<UserFollowing> {
    UserFollowing findByUserIdAndFollowingUserId (int userId , int followingUserId);
}