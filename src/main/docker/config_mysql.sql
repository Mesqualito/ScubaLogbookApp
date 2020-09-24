## Use to run mysql db docker image, optional if you're not using a local mysqldb
# see: https://bugs.mysql.com/bug.php?id=91395 and https://docs.docker.com/config/containers/resource_constraints/
# and: https://github.com/moby/moby/issues/25885
# only dev: docker run --name mysqldb -p 3306:3306 --cap-add SYS_NICE -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql
# prod min.: docker run --name mysqldb -p 3306:3306 -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql

# connect to mysql and run as mysql root user
# create Databases
CREATE DATABASE divedb_dev;
CREATE DATABASE divedb_prod;

# create database service accounts
# " ...@''%'' " means to mysql: from any host
CREATE USER 'divedb_dev_user'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'divedb_prod_user'@'localhost' IDENTIFIED BY 'password';
CREATE USER 'divedb_dev_user'@'%' IDENTIFIED BY 'password';
CREATE USER 'divedb_prod_user'@'%' IDENTIFIED BY 'password';

# Database grants
# DML ("schema privileges") only, no DDL-capabilities ("Administrative roles") for the users'
GRANT SELECT ON divedb_dev.* to 'divedb_dev_user'@'localhost';
GRANT INSERT ON divedb_dev.* to 'divedb_dev_user'@'localhost';
GRANT DELETE ON divedb_dev.* to 'divedb_dev_user'@'localhost';
GRANT UPDATE ON divedb_dev.* to 'divedb_dev_user'@'localhost';
GRANT SELECT ON divedb_prod.* to 'divedb_prod_user'@'localhost';
GRANT INSERT ON divedb_prod.* to 'divedb_prod_user'@'localhost';
GRANT DELETE ON divedb_prod.* to 'divedb_prod_user'@'localhost';
GRANT UPDATE ON divedb_prod.* to 'divedb_prod_user'@'localhost';
GRANT SELECT ON divedb_dev.* to 'divedb_dev_user'@'%';
GRANT INSERT ON divedb_dev.* to 'divedb_dev_user'@'%';
GRANT DELETE ON divedb_dev.* to 'divedb_dev_user'@'%';
GRANT UPDATE ON divedb_dev.* to 'divedb_dev_user'@'%';
GRANT SELECT ON divedb_prod.* to 'divedb_prod_user'@'%';
GRANT INSERT ON divedb_prod.* to 'divedb_prod_user'@'%';
GRANT DELETE ON divedb_prod.* to 'divedb_prod_user'@'%';
GRANT UPDATE ON divedb_prod.* to 'divedb_prod_user'@'%';
