DROP TABLE if EXISTS users;
CREATE TABLE users (
  id          CHAR(32) PRIMARY KEY,
  username    VARCHAR(50),
  password    VARCHAR(64),
  create_date TIMESTAMP,
  update_date TIMESTAMP
);

-- CREATE TABLE `test_user` (
--   `id` varchar(32) NOT NULL COMMENT '主键',
--   `testid` varchar(32) NOT NULL COMMENT 'test',
--   `name` varchar(50) NOT NULL COMMENT 'test',
--   PRIMARY KEY (`id`)
-- )