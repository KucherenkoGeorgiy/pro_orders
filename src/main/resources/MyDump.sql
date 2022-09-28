CREATE DATABASE  IF NOT EXISTS `schema_orders`;
USE `schema_orders`;

DROP TABLE IF EXISTS `order_product`;

CREATE TABLE `order_product` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `product_id_idx` (`product_id`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `order_product` WRITE;
INSERT INTO `order_product` VALUES (1,1,2),(1,2,2),(1,3,2),(1,4,2),(3,3,1),(4,2,2),(6,4,2),(9,1,2),(9,4,2),(11,1,4),(11,4,4),(12,1,8),(12,4,8),(13,1,16),(13,4,16),(20,1,2),(21,2,100),(22,2,100),(24,2,100),(25,2,200),(29,1,2),(30,2,3),(31,1,1),(32,4,5),(33,2,6),(34,2,1),(35,1,2),(39,2,500),(40,2,1000),(41,2,2000);
UNLOCK TABLES;

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `orders` WRITE;
INSERT INTO `orders` VALUES (1,'2022-09-16'),(3,'2022-09-17'),(4,'2022-08-30'),(6,'2022-09-23'),(9,'2022-09-23'),(10,'2022-09-23'),(11,'2022-09-23'),(12,'2022-09-23'),(13,'2022-09-23'),(14,'2022-09-24'),(20,'2022-09-27'),(21,'2022-09-28'),(22,'2022-09-28'),(24,'2022-09-28'),(25,'2022-09-28'),(29,'2022-09-29'),(30,'2022-09-30'),(31,'2022-10-01'),(32,'2022-10-02'),(33,'2022-10-03'),(34,'2022-10-04'),(35,'2022-10-05'),(39,'2022-09-28'),(40,'2022-09-28'),(41,'2022-09-28');
UNLOCK TABLES;

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `describing` varchar(255) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `product_name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `product` WRITE;
INSERT INTO `product` VALUES (1,'Cola','cold drink Coca-Cola',2),(2,'Fanta','cold drink Fanta',3),(3,'Sprite','cold drink Sprite',4),(4,'Vodka','Absolute',9);
UNLOCK TABLES;