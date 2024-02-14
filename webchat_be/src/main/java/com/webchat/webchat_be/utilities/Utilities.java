package com.webchat.webchat_be.utilities;

import com.webchat.webchat_be.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utilities {

    public static UserDTO getUserDTOFromContext(){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(SecurityContextHolder.getContext().getAuthentication().getPrincipal() , userDTO);
        return userDTO;
    }
}
