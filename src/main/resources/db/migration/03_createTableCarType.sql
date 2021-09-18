DROP TABLE IF EXISTS `car_type`;
CREATE TABLE `car_type` (
    `car_type_id` VARCHAR(128) NOT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    CONSTRAINT pk_car_type PRIMARY KEY (`car_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
