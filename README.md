# sharding-jdbc-demo
MySQL分库分表演示

## How to build
```shell script
git clone https://github.com/bq-xiao/sharding-jdbc-demo.git
cd sharding-jdbc-demo
mvn clean package -Dmaven.test.skip=true
```

## How to testing this project
```shell script
cd cd sharding-jdbc-demo
mvn test
```

## Table schema

```mysql
create database ds0;
use ds0;
DROP TABLE IF EXISTS `t_order0`;
CREATE TABLE IF NOT EXISTS `t_order0`
(
    `order_id`   INT UNSIGNED,
    `order_name` VARCHAR(255) NOT NULL,
    `order_date` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `t_order1`;
CREATE TABLE IF NOT EXISTS `t_order1`
(
    `order_id`   INT UNSIGNED,
    `order_name` VARCHAR(255) NOT NULL,
    `order_date` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

create database ds1;
use ds1;
DROP TABLE IF EXISTS `t_order0`;
CREATE TABLE IF NOT EXISTS `t_order0`
(
    `order_id`   INT UNSIGNED,
    `order_name` VARCHAR(255) NOT NULL,
    `order_date` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

DROP TABLE IF EXISTS `t_order1`;
CREATE TABLE IF NOT EXISTS `t_order1`
(
    `order_id`   INT UNSIGNED,
    `order_name` VARCHAR(255) NOT NULL,
    `order_date` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`order_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
```

