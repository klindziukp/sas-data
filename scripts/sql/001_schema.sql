CREATE TABLE IF NOT EXISTS `weather` (
  `id` BIGINT AUTO_INCREMENT,
  `weather` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `temperature` float(11) DEFAULT 0,
  `feels_like` float(11) DEFAULT 0,
  `wind_speed` float(11) DEFAULT 0,
  `humidity` int(3) DEFAULT 0,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `weather_duplicate` (
    `id` BIGINT AUTO_INCREMENT,
    `weather` varchar(255) NOT NULL,
    `description` varchar(255) NOT NULL,
    `temperature` float(11) DEFAULT 0,
    `feels_like` float(11) DEFAULT 0,
    `wind_speed` float(11) DEFAULT 0,
    `humidity` int(5) DEFAULT 0,
    `city` varchar(255) NOT NULL,
    `country` varchar(255) NOT NULL,
    /*  --- Flags for soft delete --- */
    `deleted_at` TIMESTAMP NULL DEFAULT NULL,
    `deleted_by` VARCHAR(255),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS `country_mapping` (
    `id` BIGINT AUTO_INCREMENT,
    `code` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;