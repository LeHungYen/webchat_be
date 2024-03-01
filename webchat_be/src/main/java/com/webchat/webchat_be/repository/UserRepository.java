package com.webchat.webchat_be.repository;

import com.webchat.webchat_be.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Optional<User> getUserByEmailAndPassword (String email , String password);
    Optional<User> findByEmail(String email);

//    List<User> findByEmailIgnoreCaseContainingOrFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrPhoneNumberIgnoreCaseContaining(String email , String fristName , String lastName , String phoneNumber , int userId);

    @Query("SELECT u FROM User u WHERE " +
            "(LOWER(REPLACE(u.email, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:email, ' ', ''), '%')) OR " +
            "LOWER(REPLACE(CONCAT(u.firstName, u.lastName), ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:fullName, ' ', ''), '%')) OR " +
            "LOWER(REPLACE(u.phoneNumber, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:phoneNumber, ' ', ''), '%'))) AND " +
            "u.userId != :userId")
    List<User> findByEmailOrFullNameOrPhoneNumberAndExcludingUserId(
            @Param("email") String email,
            @Param("fullName") String fullName,
            @Param("phoneNumber") String phoneNumber,
            @Param("userId") int userId);


    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.lastChatId = :chatId WHERE u.userId = :userId")
    void updateLastChatId(@Param("userId") Integer userId, @Param("chatId") int chatId);

}