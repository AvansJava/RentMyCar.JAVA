DROP TABLE IF EXISTS `confirmation_token`;
CREATE TABLE `confirmation_token` (
    `id` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(10) UNSIGNED NOT NULL,
    `token` VARCHAR(255) DEFAULT NULL,
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `expired_at` DATETIME DEFAULT NULL,
    `confirmed_at` DATETIME DEFAULT NULL,
    CONSTRAINT pk_confirmation_token PRIMARY KEY (`id`),
    CONSTRAINT fk_token_user FOREIGN KEY (`user_id`)
    REFERENCES user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
