# sharding-jdbc-demo
### sharding-jdbc-sharding-data
MySQL分库分表演示

### sharding-jdbc-read-write
MySQL读写分离

参考文档：https://shardingsphere.apache.org/document/legacy/4.x/document/cn/manual/sharding-jdbc/configuration/config-spring-boot/#读写分离
```shell script
root@ubuntu18:~# cat master.cnf
[mysqld]
# master server id
server-id = 1

# bin log
log_bin = mysql-master-bin
```
```shell script
root@ubuntu18:~# cat slave.cnf
[mysqld]
# slave server id
server-id = 2

# bin log
log_bin           = mysql-slave-bin
relay_log         = mysql-relay-bin
log_slave_updates = 1
read_only         = 1
```
**Master数据库配置：**
```shell script
mysql> create user 'repl'@'%' identified by 'repl-pwd';
Query OK, 0 rows affected (0.00 sec)

mysql> grant replication slave on *.* to 'repl'@'%';
Query OK, 0 rows affected (0.00 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)

mysql> show master status;
+-------------------------+----------+--------------+------------------+-------------------+
| File                    | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+-------------------------+----------+--------------+------------------+-------------------+
| mysql-master-bin.000003 |      855 |              |                  |                   |
+-------------------------+----------+--------------+------------------+-------------------+
1 row in set (0.00 sec)

mysql> alter user 'repl'@'%' identified by 'repl-pwd' password expire never;
Query OK, 0 rows affected (0.01 sec)

mysql> alter user 'repl'@'%' identified with mysql_native_password by 'repl-pwd';
Query OK, 0 rows affected (0.00 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)
```

**Slave数据库配置：**
```shell script
mysql> change master to master_host='master', master_user='repl', master_password='repl-pwd', master_log_file='mysql-master-bin.000003', master_log_pos=0;
Query OK, 0 rows affected, 2 warnings (0.01 sec)

mysql> start slave;
Query OK, 0 rows affected (0.00 sec)

mysql> show slave status\G
*************************** 1. row ***************************
               Slave_IO_State: Waiting for master to send event
                  Master_Host: master
                  Master_User: repl
                  Master_Port: 3306
                Connect_Retry: 60
              Master_Log_File: mysql-master-bin.000003
          Read_Master_Log_Pos: 1640
               Relay_Log_File: mysql-relay-bin.000002
                Relay_Log_Pos: 1869
        Relay_Master_Log_File: mysql-master-bin.000003
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB:
          Replicate_Ignore_DB:
           Replicate_Do_Table:
       Replicate_Ignore_Table:
      Replicate_Wild_Do_Table:
  Replicate_Wild_Ignore_Table:
                   Last_Errno: 0
                   Last_Error:
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 1640
              Relay_Log_Space: 2078
              Until_Condition: None
               Until_Log_File:
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File:
           Master_SSL_CA_Path:
              Master_SSL_Cert:
            Master_SSL_Cipher:
               Master_SSL_Key:
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error:
               Last_SQL_Errno: 0
               Last_SQL_Error:
  Replicate_Ignore_Server_Ids:
             Master_Server_Id: 1
                  Master_UUID: 4143f5a8-0a10-11eb-8197-0242ac110003
             Master_Info_File: mysql.slave_master_info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
           Master_Retry_Count: 86400
                  Master_Bind:
      Last_IO_Error_Timestamp:
     Last_SQL_Error_Timestamp:
               Master_SSL_Crl:
           Master_SSL_Crlpath:
           Retrieved_Gtid_Set:
            Executed_Gtid_Set:
                Auto_Position: 0
         Replicate_Rewrite_DB:
                 Channel_Name:
           Master_TLS_Version:
       Master_public_key_path:
        Get_master_public_key: 0
            Network_Namespace:
1 row in set (0.00 sec)

```

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

## Table schema (sharding-jdbc-sharding-data)

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

## Table schema (sharding-jdbc-read-write)

```mysql
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
```
