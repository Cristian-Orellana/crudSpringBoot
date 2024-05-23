-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.4.24-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para bookstore
CREATE DATABASE IF NOT EXISTS `bookstore` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci */;
USE `bookstore`;

-- Volcando estructura para tabla bookstore.category
CREATE TABLE IF NOT EXISTS `category` (
  `id_category` int(11) NOT NULL,
  `name_category` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `status_category` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla bookstore.category: ~1 rows (aproximadamente)
INSERT INTO `category` (`id_category`, `name_category`, `status_category`) VALUES
	(1, 'Mangas', b'1');

-- Volcando estructura para tabla bookstore.category_seq
CREATE TABLE IF NOT EXISTS `category_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla bookstore.category_seq: ~1 rows (aproximadamente)
INSERT INTO `category_seq` (`next_val`) VALUES
	(1);

-- Volcando estructura para tabla bookstore.editorial
CREATE TABLE IF NOT EXISTS `editorial` (
  `id_editorial` int(11) NOT NULL,
  `name_editorial` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `status_editorial` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id_editorial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla bookstore.editorial: ~10 rows (aproximadamente)
INSERT INTO `editorial` (`id_editorial`, `name_editorial`, `status_editorial`) VALUES
	(1, 'Norma', b'1'),
	(2, 'Ivrea Esp', b'1'),
	(3, 'Planeta Esp', b'1'),
	(4, 'Milky Way', b'1'),
	(5, 'Panini Esp', b'1'),
	(6, 'Ivrea Arg', b'1'),
	(7, 'Panini Arg', b'1'),
	(8, 'Planeta Arg', b'1'),
	(9, 'ECC', b'1'),
	(10, 'Distrito Manga', b'1');

-- Volcando estructura para tabla bookstore.editorial_seq
CREATE TABLE IF NOT EXISTS `editorial_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla bookstore.editorial_seq: ~1 rows (aproximadamente)
INSERT INTO `editorial_seq` (`next_val`) VALUES
	(1);

-- Volcando estructura para tabla bookstore.person
CREATE TABLE IF NOT EXISTS `person` (
  `id` int(11) NOT NULL,
  `cellphone` varchar(20) COLLATE utf8mb4_spanish_ci NOT NULL DEFAULT '0',
  `email` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `first_name` varchar(30) COLLATE utf8mb4_spanish_ci NOT NULL,
  `last_name_father` varchar(30) COLLATE utf8mb4_spanish_ci NOT NULL,
  `last_name_mother` varchar(30) COLLATE utf8mb4_spanish_ci NOT NULL,
  `rut` varchar(10) COLLATE utf8mb4_spanish_ci NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `rut` (`rut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla bookstore.person: ~10 rows (aproximadamente)
INSERT INTO `person` (`id`, `cellphone`, `email`, `first_name`, `last_name_father`, `last_name_mother`, `rut`, `status`) VALUES
	(52, '4815162485', 'corellana@gmail.com', 'Cristian', 'Orellana', 'Muñoz', '18811942-5', b'1'),
	(53, '842541458', 'baravena@gmail.com', 'Vladimir', 'Aravena', 'Fuentealba', '11111111-1', b'1'),
	(203, '45343434', 'rudeus.greyrant.lat@gmail.com', 'Rudeus', 'Grayrant', 'Latreia', '14548219-5', b'0'),
	(252, '45343434', 'barrera.marquez.gab@gmail.com', 'Gabriel', 'Barrera', 'Marquez', '16404937-K', b'0'),
	(302, '14418874', 'cris.sando.reyes@gmail.com', 'Cristian', 'Sandoval', 'Reyes', '15589145-9', b'1'),
	(306, '87447569', 'juan.arayamarquez@gmail.com', 'Juan', 'Araya', 'Marquez', '16932596-0', b'1'),
	(352, '84521117', 'aleksander.ryback@gmail.com', 'Aleksander', 'Ryback', 'Ryback', '17242706-5', b'0'),
	(402, '55847452', 'e.pinofregni@gmail.com', 'Erina', 'Fregni ', 'Pino ', '18666095-1', b'1'),
	(452, '54771124', 'anto.aguirre.mont@gmail.com', 'Antonela', 'Aguirre', 'Montaner', '17063343-1', b'0'),
	(453, '98544475', 'eris.bgreyrant@gmail.com', 'Eris', 'Boreas', 'Greyrant', '20922449-6', b'1');

-- Volcando estructura para tabla bookstore.person_seq
CREATE TABLE IF NOT EXISTS `person_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla bookstore.person_seq: ~1 rows (aproximadamente)
INSERT INTO `person_seq` (`next_val`) VALUES
	(551);

-- Volcando estructura para tabla bookstore.product
CREATE TABLE IF NOT EXISTS `product` (
  `id_product` int(11) NOT NULL,
  `isbn` bigint(20) DEFAULT NULL,
  `fourth_image_path` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `main_image_path` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `name_product` varchar(255) COLLATE utf8mb4_spanish_ci NOT NULL,
  `offer_price_product` int(11) DEFAULT NULL,
  `purchase_price_product` int(11) NOT NULL,
  `sale_price_product` int(11) NOT NULL,
  `second_image_path` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `status_product` bit(1) DEFAULT NULL,
  `stock_product` int(11) DEFAULT NULL,
  `stock_temporal_product` int(11) DEFAULT NULL,
  `third_image_path` varchar(255) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `id_category` int(11) DEFAULT NULL,
  `id_editorial` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_product`),
  UNIQUE KEY `UK_eh5ac8cccnogeps5ockuiwau5` (`isbn`),
  KEY `FK5cxv31vuhc7v32omftlxa8k3c` (`id_category`),
  KEY `FKh1jedt1e7ahgwmk1lsidnumb7` (`id_editorial`),
  CONSTRAINT `FK5cxv31vuhc7v32omftlxa8k3c` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`),
  CONSTRAINT `FKh1jedt1e7ahgwmk1lsidnumb7` FOREIGN KEY (`id_editorial`) REFERENCES `editorial` (`id_editorial`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla bookstore.product: ~1 rows (aproximadamente)
INSERT INTO `product` (`id_product`, `isbn`, `fourth_image_path`, `main_image_path`, `name_product`, `offer_price_product`, `purchase_price_product`, `sale_price_product`, `second_image_path`, `status_product`, `stock_product`, `stock_temporal_product`, `third_image_path`, `id_category`, `id_editorial`) VALUES
	(1, 12121, '', 'frieren1.jpg', 'Frieren 1', 70000, 5000, 12000, NULL, b'1', 4, 0, NULL, 1, 1);

-- Volcando estructura para tabla bookstore.product_seq
CREATE TABLE IF NOT EXISTS `product_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Volcando datos para la tabla bookstore.product_seq: ~1 rows (aproximadamente)
INSERT INTO `product_seq` (`next_val`) VALUES
	(1);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
