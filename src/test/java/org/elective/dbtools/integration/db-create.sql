USE testdb;

DROP TABLE IF EXISTS `my_table`;
CREATE TABLE `my_table` (
  `id` INT PRIMARY KEY AUTO_INCREMENT,
  `str` VARCHAR(60),
  `count` INT,
  `pass` BINARY(5),
  `flag` BOOL
) ENGINE=InnoDB;
