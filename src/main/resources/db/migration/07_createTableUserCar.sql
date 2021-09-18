DROP TABLE IF EXISTS `user_car`;
CREATE TABLE `user_car` (
   `user_id` BIGINT(10) UNSIGNED NOT NULL,
   `car_id` BIGINT(10) UNSIGNED NOT NULL,
   CONSTRAINT pk_user_car PRIMARY KEY (`user_id`, `car_id`),
   CONSTRAINT fk_user FOREIGN KEY (`user_id`)
   REFERENCES user(`id`),
   CONSTRAINT fk_car FOREIGN KEY (`car_id`)
   REFERENCES car(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;