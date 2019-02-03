CREATE DATABASE  IF NOT EXISTS `votingball`;
USE `votingball`;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `polls`;
DROP TABLE IF EXISTS `positions`;
DROP TABLE IF EXISTS `poll_positions`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `polls` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `creation_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `last_modification_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

CREATE TABLE `positions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `poll_id` int(11) NOT NULL,
  `order` int(45) NOT NULL,
  `name` varchar(255) NOT NULL,
  `votes_count` integer NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`poll_id`) REFERENCES polls(`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;


INSERT INTO polls (id,title)
VALUES (1,'Gdzie na obiad?');

INSERT INTO positions
VALUES (1,1,1,'Sułtan Kebab',0);

INSERT INTO positions
VALUES (2,1,2,'Sevi Kebab',0);

INSERT INTO positions
VALUES (3,1,3,'Kebab King',0);


INSERT INTO polls (id,title)
VALUES (2,'Jaki napój jest lepszy?');

INSERT INTO positions
VALUES (4,2,1,'Coca cola',0);

INSERT INTO positions
VALUES (5,2,2,'Pepsi',0);


SELECT * FROM polls p JOIN positions pos ON p.id = pos.poll_id;
