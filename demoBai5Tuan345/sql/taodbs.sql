-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.4.8-MariaDB
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 1. Tạo cơ sở dữ liệu
CREATE DATABASE IF NOT EXISTS `quanlynhanvienbai5` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `quanlynhanvienbai5`;

-- 2. Tạo bảng departments (Phòng ban)
CREATE TABLE IF NOT EXISTS `departments` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Thêm dữ liệu cho bảng departments
DELETE FROM `departments`;
INSERT INTO `departments` (`id`, `name`) VALUES
	(1, 'Phòng Kỹ thuật'),
	(2, 'Phòng Nhân sự'),
	(3, 'Phòng Kinh doanh');

-- 4. Tạo bảng employees (Nhân viên)
CREATE TABLE IF NOT EXISTS `employees` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `department_id` INT(11) DEFAULT NULL,
  `salary` DOUBLE DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_department` (`department_id`),
  CONSTRAINT `fk_department` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 5. Thêm dữ liệu cho bảng employees
DELETE FROM `employees`;
INSERT INTO `employees` (`id`, `name`, `department_id`, `salary`) VALUES
	(1, 'Nguyễn Văn An', 1, 15000000),
	(2, 'Trần Thị Bình', 2, 12000000),
	(3, 'Lê Văn Cường', 1, 18000000),
	(4, 'Phạm Thị Dung', 3, 10000000);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;