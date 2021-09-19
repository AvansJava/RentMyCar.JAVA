DROP TABLE IF EXISTS `driver_activity`;
CREATE TABLE `driver_activity` (
     `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
     `car_id` BIGINT(10) UNSIGNED NOT NULL,
     `reservation_number` VARCHAR(10) NOT NULL,
     `distance_driven` BIGINT(10) UNSIGNED NOT NULL,
     `top_speed` BIGINT(10) UNSIGNED NOT NULL,
     `average_speed` BIGINT(10) UNSIGNED NOT NULL,
     `acceleration` BIGINT(10) UNSIGNED NOT NULL,
     `brake` BIGINT(10) UNSIGNED NOT NULL,
     `last_synced_at` DATETIME DEFAULT NULL,
     `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
     `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
     CONSTRAINT pk_driver_activity PRIMARY KEY (`id`),
     CONSTRAINT fk_driver_activity_car FOREIGN KEY (`car_id`)
     REFERENCES car(`id`),
     CONSTRAINT fk_driver_activity_reservation FOREIGN KEY (`reservation_number`)
     REFERENCES reservation(`reservation_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
