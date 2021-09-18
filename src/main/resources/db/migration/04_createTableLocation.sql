DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
    `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(128) DEFAULT NULL,
    `street` VARCHAR(128) DEFAULT NULL,
    `house_number` VARCHAR(20) DEFAULT NULL,
    `postal_code` VARCHAR(20) DEFAULT NULL,
    `city` VARCHAR(64) DEFAULT NULL,
    `country` VARCHAR(128) DEFAULT NULL,
    `latitude` DECIMAL(10,8) DEFAULT NULL,
    `longitude` DECIMAL(11,8) DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_location PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;