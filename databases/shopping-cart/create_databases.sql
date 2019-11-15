DROP DATABASE IF EXISTS shopping_dev;
DROP DATABASE IF EXISTS shopping_test;

CREATE DATABASE shopping_dev;
CREATE DATABASE shopping_test;

CREATE USER IF NOT EXISTS 'shopping'@'localhost'
  IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON shopping_dev.* TO 'shopping' @'localhost';
GRANT ALL PRIVILEGES ON shopping_test.* TO 'shopping' @'localhost';