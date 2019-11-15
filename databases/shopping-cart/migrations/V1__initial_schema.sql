CREATE TABLE items (
  id         BIGINT(20) NOT NULL AUTO_INCREMENT,
  name       VARCHAR(250),
  price      NUMERIC,

  PRIMARY KEY (id)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8;