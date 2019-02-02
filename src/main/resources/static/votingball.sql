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
  `order` int(45) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;


CREATE TABLE `poll_positions` (
  `poll_id` int(11) NOT NULL,
  `position_id` int(11) NOT NULL,
  `votes_count` integer DEFAULT 0,
  PRIMARY KEY (poll_id,position_id),
  FOREIGN KEY (poll_id) REFERENCES polls(id),
  FOREIGN KEY (position_id) REFERENCES positions(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=UTF8;

INSERT INTO polls (id,title)
VALUES (1,'Gdzie na obiad?');

INSERT INTO positions
VALUES (1,1,'Sułtan Kebab');

INSERT INTO positions
VALUES (2,2,'Sevi Kebab');

INSERT INTO positions
VALUES (3,3,'Kebab King');

INSERT INTO poll_positions (poll_id,position_id)
VALUES (1,1);

INSERT INTO poll_positions (poll_id,position_id)
VALUES (1,2);

INSERT INTO poll_positions (poll_id,position_id)
VALUES (1,3);

SELECT * FROM polls p JOIN poll_positions pp ON p.id = pp.poll_id JOIN positions pos ON pp.position_id = pos.id;
