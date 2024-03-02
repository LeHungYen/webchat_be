create database my_webchat;
use my_webchat;

CREATE TABLE Chat (
    chatId INT AUTO_INCREMENT PRIMARY KEY,
	type varchar(50),
    name nvarchar(255),
    avatar nvarchar(255),
    emoji nvarchar(255),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP
);

create table User(
	userId int auto_increment not null primary key,
    passwordHash nvarchar(255),
    email nvarchar(255),
    firstName nvarchar(255),
	lastName nvarchar(255),
    birthdate date,
    gender nvarchar(255),
    profilePicture nvarchar(255),
    coverPhoto nvarchar(255),
    bio nvarchar(255),
    website varchar(255),
    phoneNumber varchar(255),
    lastChatId int,
    lastLogin datetime DEFAULT CURRENT_TIMESTAMP,
    registrationDate datetime DEFAULT CURRENT_TIMESTAMP,
    status nvarchar(255),
    role nvarchar(255),
	Foreign key (lastChatId) references Chat(chatId)
);

create table Post(
	postId int auto_increment not null primary key,
    userId int not null,
    content text,
    mediaType nvarchar(255),
    mediaURL varchar(255),
    tag nvarchar(255),
    location nvarchar(255),
    privacy nvarchar(255),
    createAt datetime DEFAULT CURRENT_TIMESTAMP,
	updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    Foreign key (userId) references User(userId)
);

create table Comment(
	commentId int auto_increment not null primary key,
    postId int not null, 
    userId int not null,
    content text,
    createAt datetime DEFAULT CURRENT_TIMESTAMP,
	updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key (postId) references Post(postId),
    foreign key (userId) references User(userId)
);


create table Friendship(
	friendshipId int auto_increment not null primary key,
    userId1 int not null,
    userId2 int not null,
    status nvarchar(255),
    createAt datetime default current_timestamp,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	-- check(userId1 < userId2),
    foreign key (userId1) references User(userId),
    foreign key (userId2) references User(userId)
);

create table `Group`(
	groupId int not null primary key,
    groupName nvarchar(255),
    description nvarchar(255),
    adminUserId int not null,
    createAt datetime default current_timestamp,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key(adminUserId) references User(userId)
);

create table GroupMember (
	groupMemberId int not null primary key,
    groupId int not null,
    userId int not null,
    joinDate datetime default current_timestamp,
    role nvarchar(255),
    status nvarchar(255),
    foreign key (groupId) references `Group`(groupId),
    foreign key (userId) references User(userId)
);

CREATE TABLE Notification (
    notificationId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    senderId INT NOT NULL,
	receiverId INT NOT NULL,
    type VARCHAR(255),
    link VARCHAR(255),
    isRead BOOLEAN,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (senderId) REFERENCES User(userId),
    FOREIGN KEY (receiverId) REFERENCES User(userId)
);

CREATE TABLE Report (
    reportId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    reporterUserId INT NOT NULL,
    reportedUserId INT NOT NULL,
    postId INT,
    commentId INT,
    type NVARCHAR(50),
    description TEXT,
    status NVARCHAR(20),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reporterUserId) REFERENCES User(userId),
    FOREIGN KEY (reportedUserId) REFERENCES User(userId),
    FOREIGN KEY (postId) REFERENCES Post(postId),
    FOREIGN KEY (commentId) REFERENCES Comment(commentId)
);

CREATE TABLE Poll (
    pollId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    question TEXT,
    options JSON,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    updatedAt DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE PollVote (
    voteId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    pollId INT NOT NULL,
    userId INT NOT NULL,
    votedOption INT,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (pollId) REFERENCES Poll(pollId),
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE Event (
    eventId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    startTime DATETIME,
    endTime DATETIME,
    location VARCHAR(255),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

-- Bảng Người tham gia sự kiện
CREATE TABLE EventAttendee (
    attendeeId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    eventId INT NOT NULL,
    userId INT NOT NULL,
    rsvpStatus VARCHAR(50),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (eventId) REFERENCES Event(eventId),
    FOREIGN KEY (userId) REFERENCES User(userId)
);

-- Bảng Đánh giá 
CREATE TABLE Rating (
    ratingId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ratedUserId INT NOT NULL,
    ratingUserId INT NOT NULL,
    score INT,
    review TEXT,
    isAnonymous BOOLEAN,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ratedUserId) REFERENCES User(userId),
    FOREIGN KEY (ratingUserId) REFERENCES User(userId)
);

-- Bảng Quảng cáo
CREATE TABLE Advertisement (
    adId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    advertiserUserId INT NOT NULL,
    title NVARCHAR(255),
    description TEXT,
    imageURL VARCHAR(255),
    link VARCHAR(255),
    startTime DATETIME,
    endTime DATETIME,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (advertiserUserId) REFERENCES User(userId)
);

CREATE TABLE AdClick (
    clickId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    adId INT NOT NULL,
    userId INT NOT NULL,
    clickTime DATETIME,
    FOREIGN KEY (adId) REFERENCES Advertisement(adId),
    FOREIGN KEY (userId) REFERENCES User(userId)
);
use my_webchat;


CREATE TABLE ChatParticipant (
    chatParticipantId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    chatId INT NOT NULL,
    userId INT NOT NULL,
    lastMessageSentAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    joinedAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chatId) REFERENCES Chat(chatId),
    FOREIGN KEY (userId) REFERENCES User(userId)
);

-- CREATE TABLE ChatMessage (
--     chatMessageId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
--     replyToMessageID int,
--     chatId INT NOT NULL,
--     senderUserId INT NOT NULL,
--     content TEXT,
--     mediaType VARCHAR(50),
--     mediaURL VARCHAR(255),
--     createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
--     status VARCHAR(50),
--     FOREIGN KEY (chatId) REFERENCES Chat(chatId),
--     FOREIGN KEY (senderUserId) REFERENCES User(userId),
-- 	FOREIGN KEY (replyToMessageId) REFERENCES ChatMessage(chatMessageId)
-- );

CREATE TABLE ChatMessage (
    chatMessageId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    replyToMessageID int,
    chatId INT NOT NULL,
    chatParticipantId INT NOT NULL,
    content TEXT,
	type VARCHAR(50),
    mediaType VARCHAR(50),
    mediaURL Text,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (chatId) REFERENCES Chat(chatId),
    FOREIGN KEY (chatParticipantId) REFERENCES ChatParticipant(chatParticipantId),
	FOREIGN KEY (replyToMessageId) REFERENCES ChatMessage(chatMessageId)
);

use my_webchat;
CREATE TABLE ChatMessageParticipant (
    chatMessageParticipantId INT AUTO_INCREMENT PRIMARY KEY,
    chatMessageId INT NOT NULL,
    chatParticipantId INT NOT NULL,
    lastViewedAt DATETIME, 
	status VARCHAR(50),
    FOREIGN KEY (chatMessageId) REFERENCES ChatMessage(chatMessageId),
    FOREIGN KEY (chatParticipantId) REFERENCES ChatParticipant(chatParticipantId)
);


CREATE TABLE FriendRequest (
    requestId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    senderUserId INT NOT NULL,
    receiverUserId INT NOT NULL,
    status VARCHAR(50),
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (senderUserId) REFERENCES User(userId),
    FOREIGN KEY (receiverUserId) REFERENCES User(userId)
);

CREATE TABLE UserSetting (
    settingId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    theme VARCHAR(50),
    notificationPreferences TEXT,
    language VARCHAR(50),
    privacySettings TEXT,
    createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserStat (
    statId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    postCount INT,
    friendCount INT,
    followerCount INT,
    followingCount INT,
    lastUpdate DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserAchievement (
    achievementId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    title VARCHAR(255),
    description TEXT,
    achievedAt DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserEducation (
    educationId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    schoolName VARCHAR(255),
    degree VARCHAR(255),
    fieldOfStudy VARCHAR(255),
    graduationYear INT,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserWorkExperience (
    experienceId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    companyName VARCHAR(255),
    position VARCHAR(255),
    employmentPeriod VARCHAR(255),
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserSkill (
    skillId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    skillName VARCHAR(255),
    skillLevel VARCHAR(50),
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserLanguage (
    languageId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    languageName VARCHAR(255),
    proficiencyLevel VARCHAR(50),
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserCertificate (
    certificateId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    certificateName VARCHAR(255),
    issuer VARCHAR(255),
    issueDate DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserHobbie (
    hobbyId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    hobbyName VARCHAR(255),
    description TEXT,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserProjects (
    projectId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    projectName VARCHAR(255),
    description TEXT,
    startDate DATETIME,
    endDate DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserFollowing (
    followingId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    followingUserId INT NOT NULL,
    followDate DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (followingUserId) REFERENCES User(userId)
);

CREATE TABLE UserAlbum (
    albumId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    albumName VARCHAR(255),
    description TEXT,
    createdAt DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserPhoto (
    photoId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    albumId INT NOT NULL,
    userId INT NOT NULL,
    photoURL VARCHAR(255),
    caption TEXT,
    createdAt DATETIME,
    FOREIGN KEY (albumId) REFERENCES UserAlbum(albumId),
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserVideo (
    videoId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    videoURL VARCHAR(255),
    caption TEXT,
    createdAt DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserLocationHistory (
    locationHistoryId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    latitude DECIMAL(10, 8),
    longitude DECIMAL(11, 8),
    timestamp DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserDeviceToken (
    deviceTokenId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    deviceToken VARCHAR(255),
    deviceType VARCHAR(50),
    timestamp DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserExternalAccount (
    externalAccountId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    provider VARCHAR(50),
    externalUserId VARCHAR(255),
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserSearchHistory (
    searchHistoryId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    searchQuery VARCHAR(255),
    searchTimestamp DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserWall (
    wallId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    postId INT NOT NULL,
    createdAt DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (postId) REFERENCES Post(postId)
);

-- CREATE TABLE UserReaction (
--      reactionID INT PRIMARY KEY,
--      userID INT,
--      targetType VARCHAR(255),
--      targetID_FK INT,
--      reactionType VARCHAR(50),
--      CreatedAt DATETIME NOT NULL,
--      FOREIGN KEY (userID) REFERENCES User(userID),
--      CHECK (targetType IN ('Post', 'Comment', 'Message')), -- Đảm bảo targetType chỉ nhận các giá trị cho phép
--      CHECK (
--          (targetType = 'Post' AND targetID_FK IN (SELECT postID FROM Post)) OR
--          (targetType = 'Comment' AND targetID_FK IN (SELECT commentID FROM Comment)) OR
--          (targetType = 'Message' AND targetID_FK IN (SELECT chatMessageID FROM ChatMessage))
--      ),
--      CHECK (reactionType IN ('Like', 'Love', 'Haha', 'Wow', 'Sad', 'Angry')) -- Đảm bảo reactionType chỉ nhận các giá trị cho phép
-- );



-- Tạo bảng UserReaction
CREATE TABLE UserReaction (
    reactionId INT PRIMARY KEY,
    userId INT,
    targetType VARCHAR(255),
    targetId_FK INT,
    reactionType VARCHAR(50),
    CreatedAt DATETIME NOT NULL,
    FOREIGN KEY (userId) REFERENCES User(userId),
    CHECK (targetType IN ('Post', 'Comment', 'Message')),
    CHECK (reactionType IN ('Like', 'Love', 'Haha', 'Wow', 'Sad', 'Angry'))
);


-- Tạo trigger trước khi chèn vào bảng UserReaction
DELIMITER //
CREATE TRIGGER before_insert_UserReaction
BEFORE INSERT ON UserReaction FOR EACH ROW
BEGIN
    DECLARE targetCount INT;

    IF NEW.targetType = 'Post' THEN
        SELECT COUNT(*) INTO targetCount FROM Post WHERE postId = NEW.targetId_FK;
    ELSEIF NEW.targetType = 'Comment' THEN
        SELECT COUNT(*) INTO targetCount FROM Comment WHERE commentId = NEW.targetId_FK;
    ELSEIF NEW.targetType = 'Message' THEN
        SELECT COUNT(*) INTO targetCount FROM ChatMessage WHERE chatMessageId = NEW.targetId_FK;
    END IF;

    IF targetCount = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid targetID_FK for the specified targetType';
    END IF;
END //
DELIMITER ;


CREATE TABLE UserFavorite (
    favoriteId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    targetId INT NOT NULL,
    favoriteType VARCHAR(50),
    createdAt DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId),
    FOREIGN KEY (targetId) REFERENCES Post(postId) ON DELETE CASCADE
);

CREATE TABLE UserReward (
    rewardId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    rewardType VARCHAR(50),
    description TEXT,
    createdAt DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserFeedback (
    feedbackId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    userId INT NOT NULL,
    subject VARCHAR(255),
    feedbackType VARCHAR(50),
    content TEXT,
    createdAt DATETIME,
    FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE TABLE UserAchievementProgress (
    achievementProgressId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    achievementId INT NOT NULL,
    userId INT NOT NULL,
    progressValue DECIMAL(5, 2),
    updatedAt DATETIME,
    FOREIGN KEY (achievementId) REFERENCES UserAchievement(achievementId),
    FOREIGN KEY (userId) REFERENCES User(userId)
);