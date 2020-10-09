create database ds;
use ds;
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE IF NOT EXISTS `t_order`
(
    `order_id`   INT UNSIGNED,
    `order_name` VARCHAR(255) NOT NULL,
    `order_date` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;