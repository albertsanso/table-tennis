use ttsuite;

CREATE TABLE `states` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_name_UNIQUE` (`code_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2173 DEFAULT CHARSET=utf8;

CREATE TABLE `cities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_name_UNIQUE` (`code_name`),
  KEY `fk_state_idx` (`state_id`),
  CONSTRAINT `fk_state` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4349 DEFAULT CHARSET=utf8;

CREATE TABLE `addresses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `street` varchar(255) DEFAULT NULL,
  `zip` varchar(255) NOT NULL,
  `city_id` bigint(20) NOT NULL,
  `state_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_city_idx` (`city_id`),
  KEY `fk_state_2_idx` (`state_id`),
  CONSTRAINT `fk_city` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_state_2` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5116 DEFAULT CHARSET=utf8;

CREATE TABLE `venues` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_name` varchar(65) NOT NULL,
  `name` varchar(55) NOT NULL,
  `address_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_name_UNIQUE` (`code_name`),
  KEY `fk_address_idx` (`address_id`),
  CONSTRAINT `fk_address` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3715 DEFAULT CHARSET=utf8;

CREATE TABLE `organizations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_name_UNIQUE` (`code_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4242 DEFAULT CHARSET=utf8;

CREATE TABLE `organizations_venues` (
  `organizations_venues_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `season` varchar(10) NOT NULL,
  `organization_id` bigint(20) NOT NULL,
  `venue_id` bigint(20) NOT NULL,
  PRIMARY KEY (`organizations_venues_id`),
  KEY `fk_organization_idx` (`organization_id`),
  KEY `fk_venue_idx` (`venue_id`),
  CONSTRAINT `fk_organization` FOREIGN KEY (`organization_id`) REFERENCES `organizations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_venue` FOREIGN KEY (`venue_id`) REFERENCES `venues` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3448 DEFAULT CHARSET=utf8;

CREATE TABLE `organization_aliases` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias_name` varchar(200) NOT NULL,
  `organization_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_organization_2_idx` (`organization_id`),
  CONSTRAINT `fk_organization_2` FOREIGN KEY (`organization_id`) REFERENCES `organizations` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `teams` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_name` varchar(45) NOT NULL,
  `name` varchar(75) NOT NULL,
  `organization_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code_name_UNIQUE` (`code_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

