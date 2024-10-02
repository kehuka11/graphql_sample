USE `library`;

CREATE TABLE IF NOT EXISTS `book` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `authorId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `author` (
  `authorId` varchar(255) NOT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`authorId`)
);