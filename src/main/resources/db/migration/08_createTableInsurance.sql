DROP TABLE IF EXISTS `insurance`;
CREATE TABLE `insurance` (
    `insurance_type_id` VARCHAR(128) NOT NULL,
    `description` VARCHAR(255) DEFAULT NULL,
    `price` DECIMAL(12,2) UNSIGNED DEFAULT NULL,
    CONSTRAINT pk_insurance PRIMARY KEY (`insurance_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;