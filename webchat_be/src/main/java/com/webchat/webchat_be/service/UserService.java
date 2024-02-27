package com.webchat.webchat_be.service;

import com.webchat.webchat_be.dto.UserDTO;
import com.webchat.webchat_be.dto.search.SearchUser;
import com.webchat.webchat_be.entity.User;
import com.webchat.webchat_be.enums.UserStatus;
import com.webchat.webchat_be.utilities.Utilities;
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


    public List<SearchUser>  searchUserByKey(String keySearch) {
        keySearch = keySearch.trim();
        List<SearchUser> searchUsers = new ArrayList<>();
        if(keySearch == "" || keySearch == null){
            return searchUsers;
        }

        List<User> users = userRepository.findByEmailIgnoreCaseContainingOrFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrPhoneNumberIgnoreCaseContaining(keySearch , keySearch , keySearch, keySearch);

        if(users.size() > 0){
            for ( User user: users) {
                searchUsers.add(new SearchUser( user.getUserId() ,user.getProfilePicture() , user.getFirstName(), user.getLastName(), user.getPhoneNumber() , user.getEmail() ,user.getUserReactions().size(), user.getFollowing().size() , user.getFollower().size()));
            }
        }
        return searchUsers;
    }

    public void updateStatusOnline(int userId) {
        User bean = requireOne(userId);
        bean.setStatus(String.valueOf(UserStatus.ONLINE));
        userRepository.save(bean);
    }

    public void updateStatusOffline(int userId) {
        User bean = requireOne(userId);
        bean.setStatus(String.valueOf(UserStatus.OFFLINE));
        bean.setLastLogin(new Date());
        userRepository.save(bean);
    }

    public boolean checkGmailAlreadyExisted(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            return true;
        }
        return false;
    }

}

