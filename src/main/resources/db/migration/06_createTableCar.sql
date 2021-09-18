DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
    `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(128) DEFAULT NULL,
    `brand` VARCHAR(128) DEFAULT NULL,
    `brand_type` VARCHAR(128) DEFAULT NULL,
    `car_type_id` VARCHAR(128) DEFAULT NULL,
    `mileage` INT(10) UNSIGNED DEFAULT NULL,
    `consumption` INT(10) UNSIGNED DEFAULT NULL,
    `user_id` BIGINT(10) UNSIGNED NOT NULL,
    `location_id` BIGINT(10) UNSIGNED NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_car PRIMARY KEY (`id`),
    CONSTRAINT fk_car_car_type FOREIGN KEY (`car_type_id`)
    REFERENCES car_type(`car_type_id`),
    CONSTRAINT fk_car_user FOREIGN KEY (`user_id`)
    REFERENCES user(`id`),
    CONSTRAINT fk_car_location FOREIGN KEY (`location_id`)
    REFERENCES location(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;