package com.webchat.webchat_be.dto.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchUser {
    private int userId;
    private String profilePicture;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private int likes;
    private int followings;
    private int followers;
}
