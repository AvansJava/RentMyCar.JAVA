DROP TABLE IF EXISTS `car_timeslot_availability`;
CREATE TABLE `car_timeslot_availability` (
    `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `car_id` BIGINT(10) UNSIGNED NOT NULL,
    `timeslot_id` BIGINT(10) UNSIGNED NOT NULL,
    `product_id` BIGINT(10) UNSIGNED NOT NULL,
    `start_at` DATETIME DEFAULT NULL,
    `end_at` DATETIME DEFAULT NULL,
    `status` ENUM('OPEN','CLOSED') NOT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_car_timeslot_availability PRIMARY KEY (`id`),
    CONSTRAINT fk_availability_car FOREIGN KEY (`car_id`)
    REFERENCES car(`id`),
    CONSTRAINT fk_availability_timeslot FOREIGN KEY (`timeslot_id`)
    REFERENCES timeslot(`id`),
    CONSTRAINT fk_availability_product FOREIGN KEY (`product_id`)
    REFERENCES product(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
