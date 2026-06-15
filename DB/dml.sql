LOCK TABLES `employee` WRITE;
INSERT INTO `employee` VALUES (1,'john@example.com','00001','John',NULL),(2,'ian@example.com','00002','Ian',NULL),(3,'jeff@example.com','00003','Jeff',NULL);
UNLOCK TABLES;

LOCK TABLES `seat` WRITE;
INSERT INTO `seat` VALUES (1,'1','1'),(2,'1','2'),(3,'1','3'),(4,'1','4'),(5,'2','1'),(6,'2','2'),(7,'2','3'),(8,'2','4'),(9,'3','1'),(10,'3','2'),(11,'3','3'),(12,'3','4'),(13,'4','1'),(14,'4','2'),(15,'4','3'),(16,'4','4');
UNLOCK TABLES;