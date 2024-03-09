create database instaDB;
use instaDB;

CREATE TABLE `User` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `introduce` varchar(200) NOT NULL,
  `birth` varchar(50) NOT NULL,
  `profile_img` LONGBLOB,
  `password` varchar(50) NOT NULL,
  `sex` varchar(50) NOT NULL,
  `telcom` varchar(255) NOT NULL
);

CREATE TABLE `Feed` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` int NOT NULL,
  `content` varchar(100) NOT NULL,
  `feed_img` LONGBLOB,
  `time` datetime NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `feed_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Feed_Like` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `feed_id` int NOT NULL,
  `user_id` int NOT NULL,
  `bool` tinyint NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `feed_id` (`feed_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `feed_like_ibfk_1` FOREIGN KEY (`feed_id`) REFERENCES `Feed` (`id`),
  CONSTRAINT `feed_like_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Comment` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` int NOT NULL,
  `feed_id` int NOT NULL,
  `content` varchar(200) NOT NULL,
  `time` datetime NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `user_id` (`user_id`),
  KEY `feed_id` (`feed_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`feed_id`) REFERENCES `Feed` (`id`)
);

CREATE TABLE `Comment_Like` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `cmt_id` int NOT NULL,
  `user_id` int NOT NULL,
  `bool` tinyint NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `cmt_id` (`cmt_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_like_ibfk_1` FOREIGN KEY (`cmt_id`) REFERENCES `Comment` (`id`),
  CONSTRAINT `comment_like_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Comment_Reply` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `cmt_id` int NOT NULL,
  `user_id` int NOT NULL,
  `content` varchar(200) NOT NULL,
  `time` datetime NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `cmt_id` (`cmt_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_reply_ibfk_1` FOREIGN KEY (`cmt_id`) REFERENCES `Comment` (`id`),
  CONSTRAINT `comment_reply_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Comment_Reply_Like` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `cmt_re_id` int NOT NULL,
  `user_id` int NOT NULL,
  `bool` tinyint NOT NULL,
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `cmt_id` (`cmt_re_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_reply_like_ibfk_1` FOREIGN KEY (`cmt_re_id`) REFERENCES `Comment_Reply` (`id`),
  CONSTRAINT `comment_reply_like_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Follow` (
  `id` int NOT NULL AUTO_INCREMENT,
  `who_id` int NOT NULL,
  `whom_id` int NOT NULL,
  `follow_status` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `follow_who_fk` (`who_id`),
  KEY `follow_whom_fk` (`whom_id`),
  CONSTRAINT `follow_who_fk` FOREIGN KEY (`who_id`) REFERENCES `User` (`id`),
  CONSTRAINT `follow_whom_fk` FOREIGN KEY (`whom_id`) REFERENCES `User` (`id`)
);

CREATE TABLE `Search` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `user_id` INT NOT NULL,
  `searched_user_id` INT NOT NULL,
  FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  FOREIGN KEY (`searched_user_id`) REFERENCES `User` (`id`)
);

