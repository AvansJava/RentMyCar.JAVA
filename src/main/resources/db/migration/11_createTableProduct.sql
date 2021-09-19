DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
   `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
   `reservation_number` VARCHAR(10) NOT NULL,
   `rental_plan_id` BIGINT(10) UNSIGNED NOT NULL,
   `price` DECIMAL(12,2) UNSIGNED DEFAULT NULL,
   `status` ENUM('COMPLETED','PENDING', 'CANCELED', 'EXPIRED') NOT NULL,
   `paid_at` DATETIME DEFAULT NULL,
   `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   CONSTRAINT pk_product PRIMARY KEY (`id`),
   CONSTRAINT fk_product_reservation FOREIGN KEY (`reservation_number`)
   REFERENCES reservation(`reservation_number`),
   CONSTRAINT fk_product_rental_plan FOREIGN KEY (`rental_plan_id`)
   REFERENCES rental_plan(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;