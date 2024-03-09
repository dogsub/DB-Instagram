본래 코드 - 

create database instaDB;
use instaDB;

CREATE TABLE `Login` (
  `email` varchar(50) NOT NULL,
  `pw` varchar(50) NOT NULL,
  PRIMARY KEY (`email`),
  UNIQUE KEY `id_UNIQUE` (`email`)
);

CREATE TABLE `User` (
  `id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `introduce` varchar(200) NOT NULL,
  `birth` varchar(50) NOT NULL,
  `profile_img` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `email` (`email`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`email`) REFERENCES `Login` (`email`)
);

CREATE TABLE `Feed` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` varchar(100) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `feed_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Feed_Like` (
  `feed_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY `feed_id` (`feed_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `feed_like_ibfk_1` FOREIGN KEY (`feed_id`) REFERENCES `Feed` (`id`),
  CONSTRAINT `feed_like_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Feed_Img` (
  `id` int NOT NULL,
  `feed_id` int NOT NULL,
  `imgurl` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `feed_id` (`feed_id`),
  CONSTRAINT `feed_img_ibfk_1` FOREIGN KEY (`feed_id`) REFERENCES `Feed` (`id`)
);

CREATE TABLE `Hashtag` (
  `id` int NOT NULL,
  `hashtag` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

CREATE TABLE `Feed_Hashtag` (
  `feed_id` int NOT NULL,
  `hash_id` int NOT NULL,
  KEY `feed_id` (`feed_id`),
  KEY `hash_id` (`hash_id`),
  CONSTRAINT `feed_hashtag_ibfk_1` FOREIGN KEY (`feed_id`) REFERENCES `Feed` (`id`),
  CONSTRAINT `feed_hashtag_ibfk_2` FOREIGN KEY (`hash_id`) REFERENCES `Hashtag` (`id`)
);

CREATE TABLE `Feed_TagUser` (
  `feed_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY `feed_id` (`feed_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `feed_taguser_ibfk_1` FOREIGN KEY (`feed_id`) REFERENCES `Feed` (`id`),
  CONSTRAINT `feed_taguser_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Comment` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `feed_id` int NOT NULL,
  `content` varchar(200) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id` (`user_id`),
  KEY `feed_id` (`feed_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`feed_id`) REFERENCES `Feed` (`id`)
);

CREATE TABLE `Comment_Hashtag` (
  `cmt_id` int NOT NULL,
  `hash_id` int NOT NULL,
  KEY `cmt_id` (`cmt_id`),
  KEY `hash_id` (`hash_id`),
  CONSTRAINT `comment_hashtag_ibfk_1` FOREIGN KEY (`cmt_id`) REFERENCES `Comment` (`id`),
  CONSTRAINT `comment_hashtag_ibfk_2` FOREIGN KEY (`hash_id`) REFERENCES `Hashtag` (`id`)
);

CREATE TABLE `Comment_TagUser` (
  `cmt_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY `cmt_id` (`cmt_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_taguser_ibfk_1` FOREIGN KEY (`cmt_id`) REFERENCES `Comment` (`id`),
  CONSTRAINT `comment_taguser_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Comment_Like` (
  `cmt_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY `cmt_id` (`cmt_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_like_ibfk_1` FOREIGN KEY (`cmt_id`) REFERENCES `Comment` (`id`),
  CONSTRAINT `comment_like_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Comment_Reply` (
  `cmt_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` varchar(200) NOT NULL,
  `time` datetime NOT NULL,
  KEY `cmt_id` (`cmt_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_reply_ibfk_1` FOREIGN KEY (`cmt_id`) REFERENCES `Comment` (`id`),
  CONSTRAINT `comment_reply_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Story` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `story_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Story_Img` (
  `id` int NOT NULL,
  `story_id` int NOT NULL,
  `imgurl` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `story_id` (`story_id`),
  CONSTRAINT `story_img_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `Story` (`id`)
);

CREATE TABLE `Story_Like` (
  `story_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY `story_id` (`story_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `story_like_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `Story` (`id`),
  CONSTRAINT `story_like_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Story_TagUser` (
  `story_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY `story_id` (`story_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `story_taguser_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `Story` (`id`),
  CONSTRAINT `story_taguser_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Story_Hashtag` (
  `story_id` int NOT NULL,
  `hash_id` int NOT NULL,
  KEY `story_id` (`story_id`),
  KEY `hash_id` (`hash_id`),
  CONSTRAINT `story_hashtag_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `Story` (`id`),
  CONSTRAINT `story_hashtag_ibfk_2` FOREIGN KEY (`hash_id`) REFERENCES `Hashtag` (`id`)
);

CREATE TABLE `Story_Watched` (
  `story_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY `story_id` (`story_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `story_watched_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `Story` (`id`),
  CONSTRAINT `story_watched_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `BestFriend` (
  `who_id` int NOT NULL,
  `whom_id` int NOT NULL,
  KEY `who_id` (`who_id`),
  KEY `whom_id` (`whom_id`),
  CONSTRAINT `bestfriend_ibfk_1` FOREIGN KEY (`who_id`) REFERENCES `User` (`id`),
  CONSTRAINT `bestfriend_ibfk_2` FOREIGN KEY (`whom_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Block` (
  `who_id` int NOT NULL,
  `whom_id` int NOT NULL,
  KEY `who_id` (`who_id`),
  KEY `whom_id` (`whom_id`),
  CONSTRAINT `block_ibfk_1` FOREIGN KEY (`who_id`) REFERENCES `User` (`id`),
  CONSTRAINT `block_ibfk_2` FOREIGN KEY (`whom_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Follow` (
  `who_id` int NOT NULL,
  `whom_id` int NOT NULL,
  KEY `who_id` (`who_id`),
  KEY `whom_id` (`whom_id`),
  CONSTRAINT `follow_ibfk_1` FOREIGN KEY (`who_id`) REFERENCES `User` (`id`),
  CONSTRAINT `follow_ibfk_2` FOREIGN KEY (`whom_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Report` (
  `id` int NOT NULL,
  `content` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
);

CREATE TABLE `Feed_Report` (
  `feed_id` int NOT NULL,
  `report_id` int NOT NULL,
  KEY `feed_id` (`feed_id`),
  KEY `report_id` (`report_id`),
  CONSTRAINT `feed_report_ibfk_1` FOREIGN KEY (`feed_id`) REFERENCES `Feed` (`id`),
  CONSTRAINT `feed_report_ibfk_2` FOREIGN KEY (`report_id`) REFERENCES `Report` (`id`)
);

CREATE TABLE `Story_Report` (
  `story_id` int NOT NULL,
  `report_id` int NOT NULL,
  KEY `story_id` (`story_id`),
  KEY `report_id` (`report_id`),
  CONSTRAINT `story_report_ibfk_1` FOREIGN KEY (`story_id`) REFERENCES `Story` (`id`),
  CONSTRAINT `story_report_ibfk_2` FOREIGN KEY (`report_id`) REFERENCES `Report` (`id`)
);

CREATE TABLE `Comment_Report` (
  `cmt_id` int NOT NULL,
  `report_id` int NOT NULL,
  KEY `cmt_id` (`cmt_id`),
  KEY `report_id` (`report_id`),
  CONSTRAINT `comment_report_ibfk_1` FOREIGN KEY (`cmt_id`) REFERENCES `Comment` (`id`),
  CONSTRAINT `comment_report_ibfk_2` FOREIGN KEY (`report_id`) REFERENCES `Report` (`id`)
);

CREATE TABLE `Message` (
  `who_id` int NOT NULL,
  `whom_id` int NOT NULL,
  `content` varchar(100) NOT NULL,
  `imgurl` varchar(100) NOT NULL,
  KEY `who_id` (`who_id`),
  KEY `whom_id` (`whom_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`who_id`) REFERENCES `User` (`id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`whom_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Magnifier` (
  `id` int NOT NULL,
  `whom_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `whom_id` (`whom_id`),
  CONSTRAINT `magnifier_ibfk_1` FOREIGN KEY (`whom_id`) REFERENCES `User` (`id`)
);