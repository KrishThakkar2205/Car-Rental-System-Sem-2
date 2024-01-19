-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 19, 2024 at 08:12 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `car_rental`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `addBooking_car` (IN `carid` INT, IN `userid` BIGINT, IN `bookdate` DATE, IN `enddate` DATE, IN `chargeperkm` BIGINT)   BEGIN
insert into booking_car (car_id,user_id,end_date_time,book_date,charge_perkm) VALUES (carid,userid,enddate,bookdate,chargeperkm);
update car set booked=1 where car_id=carid;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `booking_car`
--

CREATE TABLE `booking_car` (
  `booking_id` int(11) NOT NULL,
  `car_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `end_date_time` date DEFAULT NULL,
  `charge_perkm` bigint(20) DEFAULT NULL,
  `km_driven` bigint(20) DEFAULT NULL,
  `book_date` date DEFAULT NULL,
  `Payment` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking_car`
--

INSERT INTO `booking_car` (`booking_id`, `car_id`, `user_id`, `end_date_time`, `charge_perkm`, `km_driven`, `book_date`, `Payment`) VALUES
(1, 12, 1, '2023-10-07', 15, 500, '2023-10-06', 7500),
(2, 11, 2, '2023-10-07', 25, 1500, '2023-10-07', 39500);

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

CREATE TABLE `car` (
  `car_id` int(11) NOT NULL,
  `color` text DEFAULT NULL,
  `name` text DEFAULT NULL,
  `fuel` varchar(20) DEFAULT NULL,
  `car_type` varchar(20) DEFAULT NULL,
  `booked` tinyint(1) DEFAULT NULL,
  `odometer` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`car_id`, `color`, `name`, `fuel`, `car_type`, `booked`, `odometer`) VALUES
(2, 'white', 'Audi Q5', 'Petrol', 'SUV', 0, NULL),
(3, 'white', 'Audi Q7', 'Petrol', 'SUV', 0, NULL),
(4, 'white', 'Audi Q8', 'Petrol', 'SUV', 0, NULL),
(5, 'white', 'Audi E-tron', 'Electric', 'SUV', 0, NULL),
(6, 'white', 'Audi A3', 'Petrol', 'Sedan', 0, NULL),
(7, 'white', 'Audi A4', 'Petrol', 'Sedan', 0, NULL),
(8, 'white', 'Audi A6', 'Petrol', 'Sedan', 0, NULL),
(9, 'white', 'Audi A8', 'Petrol', 'Sedan', 0, NULL),
(10, 'white', 'Audi S5', 'Petrol', 'Sedan', 0, NULL),
(11, 'white', 'Audi RS5', 'Petrol', 'Sedan', 0, 1500),
(12, 'black', 'Audi E-tron GT', 'Electric', 'Sedan', 0, 500),
(13, 'white', 'Mercedes Benz GLA', 'Petrol', 'SUV', 0, NULL),
(14, 'white', 'Mercedes Benz E-Class', 'Petrol', 'Sedan', 0, NULL),
(15, 'black', 'Mercedes Benz GLS', 'Diesel', 'SUV', 0, NULL),
(16, 'blue', 'Mercedes Benz S-Class', 'Diesel', 'Sedan', 0, NULL),
(17, 'black', 'Mercedes Benz EQS', 'Electric', 'SUV', 0, NULL),
(18, 'white', 'Mercedes Benz GLC', 'Petrol', 'SUV', 0, NULL),
(19, 'blue', 'Mercedes Benz C-Class', 'Diesel', 'Sedan', 0, NULL),
(20, 'black', 'Mercedes Benz GLE', 'Diesel', 'SUV', 0, NULL),
(21, 'black', 'Mercedes Benz G-Class', 'Diesel', 'SUV', 0, NULL),
(22, 'blue', 'Mercedes Benz EQE', 'Electric', 'SUV', 0, NULL),
(23, 'red', 'Mercedes Benz AMG SL', 'Hybrid', 'Sedan', 0, NULL),
(24, 'blue', 'Maruti Ignis', 'CNG', 'HatchBack', 0, 1500);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `email_id` text DEFAULT NULL,
  `phone_no` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  `user_name` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`email_id`, `phone_no`, `user_id`, `user_name`) VALUES
('kdthakkar22@gmail.com', 9099368070, 1, 'Krish Thakkar'),
('deep_vidhya214@yahoo.com', 9898073274, 2, 'Deepak Thakkar'),
('bhavana_thakkar@gmail.com', 9374041213, 3, 'Bhavana Thakkar'),
('chandrakantthakkar19@gmail.com', 9099543846, 4, 'Chandarkant Thakkar'),
('jjanuvidhya@gmail.com', 9099363054, 5, 'Jayshree Thakkar'),
('jyoti_thakkar@gmail.com', 7886234591, 6, 'Jyoti Thakkar'),
('yash_thakkar45@gmail.com', 6354779831, 7, 'Yash Thakkar'),
('pritesh_ruparaliya@gmail.com', 9924130937, 8, 'Pritesh Ruparaliya'),
('tirth_thakkar@gmail.com', 9408123937, 9, 'Tirth Thakkar'),
('alka_pujara@yahoo.com', 7984778478, 10, 'Alka Pujara'),
('mehul_thakkr@gmail.com', 9377770307, 11, 'Mehul Thakkar'),
('jay_shah@gmail.com', 9376124435, 12, 'Jay Shah'),
('zeni_patel@yahoo.com', 9825160522, 13, 'Zeni Patel'),
('yash_rathod@gmail.com', 7600467722, 14, 'Yash Rathod'),
('vrz_thakor@yahoo.com', 7778920762, 15, 'Vrz Thakor'),
('malvi_kanadiya@gmail.com', 7433862044, 16, 'Malvi Kanadiya'),
('smit_shah@gmail.com', 7600295638, 17, 'Smit Shah'),
('shreyanshi_prajapati@gmail.com', 9512530020, 18, 'Sheryanshi Prajapati'),
('deepak_prajapati@yahoo.com', 8732931845, 19, 'Deepak Prajapati'),
('ramesh_padhiyargmail.com', 7984020056, 20, 'Ramesh Padhiyar'),
('kdthakkar22@gmail.com\'', 9099368070, 22, 'Krish Thakkar');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking_car`
--
ALTER TABLE `booking_car`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `car_id` (`car_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`car_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking_car`
--
ALTER TABLE `booking_car`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `car_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking_car`
--
ALTER TABLE `booking_car`
  ADD CONSTRAINT `booking_car_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
