DROP TABLE IF EXISTS `seat`;

CREATE TABLE `seat` (
  `floor_seat_seq` bigint NOT NULL AUTO_INCREMENT,
  `floor_no` varchar(255) DEFAULT NULL,
  `seat_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`floor_seat_seq`),
  UNIQUE KEY `UKitsaecmeqmr35gdxd6ukcihe6` (`floor_no`,`seat_no`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `employee_id` varchar(5) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `floor_seat_seq` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKmc5x07dj0uft9opsxchp0uwji` (`employee_id`),
  UNIQUE KEY `UKe76ogoko7krda86ot17qknt31` (`floor_seat_seq`),
  CONSTRAINT `FKk1wsigh9ggge2lfjdkm3xad` FOREIGN KEY (`floor_seat_seq`) REFERENCES `seat` (`floor_seat_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;