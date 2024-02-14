package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserDTO;
import com.webchat.webchat_be.dto.search.SearchUser;
import com.webchat.webchat_be.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.webchat.webchat_be.repository.UserRepository;
import com.webchat.webchat_be.vo.UserQueryVO;
import com.webchat.webchat_be.vo.UserUpdateVO;
import com.webchat.webchat_be.vo.UserVO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    private final JdbcTemplate jdbcTemplate;

//    public UserService(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }


    public Integer save(UserVO vO) {
        User bean = new User();
        BeanUtils.copyProperties(vO, bean);
        bean = userRepository.save(bean);
        return bean.getUserId();
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void update(Integer id, UserUpdateVO vO) {
        User bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        userRepository.save(bean);
    }

    public UserDTO getById(Integer id) {
        User original = requireOne(id);
        return toDTO(original);
    }

    public UserDTO getByEmailAndPassword(UserVO vO) {
        User original = userRepository.getUserByEmailAndPassword(vO.getEmail() , vO.getPassword()).orElseThrow(() -> new NoSuchElementException("Account not exist"));
        return toDTO(original);
    }

    public Page<UserDTO> query(UserQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private UserDTO toDTO(User original) {
        UserDTO bean = new UserDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private User requireOne(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public List<SearchUser> searchUserByKey(String keySearch) {
//        String sql = "SELECT " +
//                "    u.fullName, " +
//                "    u.phoneNumber, " +
//                "    u.email, " +
//                "    COALESCE(ur.totalUserReaction, 0) AS totalLikes, " +
//                "    COALESCE(uf.totalUserFollowing, 0) AS following, " +
//                "    COALESCE(uf2.totalUserFollower, 0) AS follower " +
//                "FROM " +
//                "    User u " +
//                "LEFT JOIN ( " +
//                "    SELECT " +
//                "        userId, " +
//                "        COUNT(reactionId) AS totalUserReaction " +
//                "    FROM " +
//                "        UserReaction " +
//                "    GROUP BY " +
//                "        userId " +
//                ") AS ur ON u.userId = ur.userId " +
//                "LEFT JOIN ( " +
//                "    SELECT " +
//                "        userId, " +
//                "        COUNT(followingId) AS totalUserFollowing " +
//                "    FROM " +
//                "        UserFollowing " +
//                "    GROUP BY " +
//                "        userId " +
//                ") AS uf ON u.userId = uf.userId " +
//                "LEFT JOIN ( " +
//                "    SELECT " +
//                "        followingUserId, " +
//                "        COUNT(followingId) AS totalUserFollower " +
//                "    FROM " +
//                "        UserFollowing " +
//                "    GROUP BY " +
//                "        followingUserId " +
//                ") AS uf2 ON u.userId = uf2.followingUserId " +
//                "WHERE " +
//                "    u.fullName LIKE CONCAT('%', ?, '%') " +
//                "    OR u.email LIKE CONCAT('%', ?, '%') " +
//                "    OR u.phoneNumber LIKE CONCAT('%', ?, '%')";
//
////        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, keySearch, keySearch, keySearch);
////
////        return rows.stream()
////                .map(row -> {
////                    SearchUser user = new SearchUser();
////                    user.setFullName((String) row.get("fullName"));
////                    user.setPhoneNumber((String) row.get("phoneNumber"));
////                    user.setEmail((String) row.get("email"));
////                    user.setTotalLikes((Long) row.get("totalLikes"));
////                    user.setFollowing((Long) row.get("following"));
////                    user.setFollower((Long) row.get("follower"));
////                    return user;
////                })
////                .collect(Collectors.toList());
//
//        return jdbcTemplate.query(sql, new Object[]{keySearch, keySearch, keySearch}, new BeanPropertyRowMapper<>(SearchUser.class));
//    }

    public List<SearchUser>  searchUserByKey(String keySearch) {
         List<User> users = userRepository.findByEmailIgnoreCaseContainingOrFullNameIgnoreCaseContainingOrPhoneNumberIgnoreCaseContaining(keySearch , keySearch , keySearch);
        List<SearchUser> searchUsers = new ArrayList<>();

        for ( User user: users) {
            searchUsers.add(new SearchUser( user.getUserId() ,user.getProfilePicture() , user.getFullName() , user.getPhoneNumber() , user.getEmail() ,user.getUserReactions().size(), user.getFollowing().size() , user.getFollower().size()));
        }
        return searchUsers;
    }

}

