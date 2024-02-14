package com.webchat.webchat_be.entity;

import com.webchat.webchat_be.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable , UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "userId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "username")
    private String username;

    @Column(name = "passwordHash")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "gender")
    private String gender;

    @Column(name = "profilePicture")
    private String profilePicture;

    @Column(name = "coverPhoto")
    private String coverPhoto;

    @Column(name = "bio")
    private String bio;

    @Column(name = "website")
    private String website;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "lastLogin")
    private Date lastLogin;

    @Column(name = "registrationDate")
    private Date registrationDate;

    @Column(name = "status")
    private String status;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "participant1Id")
    private List<Chat> chatsAsParticipant1;

    @OneToMany(mappedBy = "participant2Id")
    private List<Chat> chatsAsParticipant2;

    @OneToMany(mappedBy = "user1")
    private List<Friendship> friendships1;

    @OneToMany(mappedBy = "user2")
    private List<Friendship> friendships2;

    @OneToMany(mappedBy = "senderUser")
    private List<Friendrequest> send;

    @OneToMany(mappedBy = "receiverUser")
    private List<Friendrequest> receive;

    @OneToMany(mappedBy = "user")
    private List<Chatmessage> chatMessages;

    @OneToMany(mappedBy = "ratedUser")
    private List<Rating> rated;

    @OneToMany(mappedBy = "ratingUser")
    private List<Rating> rating;

    @OneToMany(mappedBy = "reporter")
    private List<Report> reporterUser;

    @OneToMany(mappedBy = "reportedUser")
    private List<Report> reportedUser;

    @OneToMany(mappedBy = "user")
    private List<Group> groups;

    @OneToMany(mappedBy = "user")
    private List<UserFollowing> following;

    @OneToMany(mappedBy = "followingUser")
    private List<UserFollowing> follower;

    @OneToMany(mappedBy = "user")
    private List<Advertisement> advertisements;

    @OneToMany(mappedBy = "senderId")
    private List<Notification> notificationsSender;

    @OneToMany(mappedBy = "receiverId")
    private List<Notification> notificationsReceiver;

    @OneToMany(mappedBy = "user")
    private List<Adclick> adclicks;

    @OneToMany(mappedBy = "user")
    private List<Usereducation> userEducations;

    @OneToMany(mappedBy = "user")
    private List<Pollvote> pollVotes;

    @OneToMany(mappedBy = "user")
    private List<Userskill> userSkills;

    @OneToMany(mappedBy = "user")
    private List<Userreaction> userReactions;

    @OneToMany(mappedBy = "user")
    private List<Userphoto> userPhotos;

    @OneToMany(mappedBy = "user")
    private List<Userfeedback> userFeedbacks;

    @OneToMany(mappedBy = "user")
    private List<Userprojects> userProjects;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Uservideo> userVideos;

    @OneToMany(mappedBy = "user")
    private List<Userdevicetoken> userDeviceTokens;

    @OneToMany(mappedBy = "user")
    private List<Event> events;

    @OneToMany(mappedBy = "user")
    private List<Userlocationhistory> userLocationHistories;

    @OneToMany(mappedBy = "user")
    private List<Userachievementprogress> userAchievementProgresses;

    @OneToMany(mappedBy = "user")
    private List<Usersearchhistory> userSearchHistories;

    @OneToMany(mappedBy = "user")
    private List<Userlanguage> userlanguages;

    @OneToMany(mappedBy = "user")
    private List<Groupmember> groupMembers;

    @OneToMany(mappedBy = "user")
    private List<Usersetting> userSettings;

    @OneToMany(mappedBy = "user")
    private List<Userworkexperience> userWorkExperiences;

    @OneToMany(mappedBy = "user")
    private List<Userexternalaccount> userExternalAccounts;

    @OneToMany(mappedBy = "user")
    private List<Userwall> userwalls; ////////////////////////////////// sửa lại mối quan hệ one to one

    @OneToMany(mappedBy = "user")
    private List<Userstat> userStats;

    @OneToMany(mappedBy = "user")
    private List<Poll> polls;

    @OneToMany(mappedBy = "user")
    private List<Userreward> userRewards;

    @OneToMany(mappedBy = "user")
    private List<Userfavorite> userFavorites;

    @OneToMany(mappedBy = "user")
    private List<Userhobbie> userHobbies;

    @OneToMany(mappedBy = "user")
    private List<Useralbum> userAlbums;

    @OneToMany(mappedBy = "user")
    private List<UserCertificate> userCertificates;

    @OneToMany(mappedBy = "user")
    private List<Userachievement> userAchievements;

    @OneToMany(mappedBy = "user")
    private List<Eventattendee> eventAttendees;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(userRole.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
