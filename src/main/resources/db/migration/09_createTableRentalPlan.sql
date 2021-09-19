DROP TABLE IF EXISTS `rental_plan`;
CREATE TABLE `rental_plan` (
   `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   `car_id` BIGINT(10) UNSIGNED NOT NULL,
   `available_from` DATETIME DEFAULT NULL,
   `available_until` DATETIME DEFAULT NULL,
   `price` DECIMAL(12,2) UNSIGNED DEFAULT NULL,
   `distance` INT(10) UNSIGNED DEFAULT NULL,
   `insurance_type_id` VARCHAR(128) DEFAULT NULL,
   `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CONSTRAINT pk_rental_plan PRIMARY KEY (`id`),
   CONSTRAINT fk_car_rental_plan FOREIGN KEY (`car_id`)
   REFERENCES car(`id`),
   CONSTRAINT fk_rental_plan_insurance FOREIGN KEY (`insurance_type_id`)
   REFERENCES insurance(`insurance_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;