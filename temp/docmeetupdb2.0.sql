-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 30, 2023 at 08:51 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `docmeetupdb1.0`
--

-- --------------------------------------------------------

--
-- Table structure for table `analysis`
--

CREATE TABLE `analysis` (
  `analysis_id` int(11) NOT NULL,
  `analysis_name` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `appointment_id` int(11) NOT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `appointment_date` date DEFAULT NULL,
  `appointment_time` time DEFAULT NULL,
  `calendar_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

CREATE TABLE `consultation` (
  `consultation_id` int(11) NOT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `consultation_date` date DEFAULT NULL,
  `consultation_time` time DEFAULT NULL,
  `notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `consultationanalysis`
--

CREATE TABLE `consultationanalysis` (
  `consultation_id` int(11) NOT NULL,
  `analysis_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `consultationmedication`
--

CREATE TABLE `consultationmedication` (
  `consultation_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doctor_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `specialty` varchar(50) DEFAULT NULL,
  `license_number` varchar(50) DEFAULT NULL,
  `clinic_name` varchar(100) DEFAULT NULL,
  `clinic_address_line1` varchar(100) DEFAULT NULL,
  `clinic_address_line2` varchar(100) DEFAULT NULL,
  `clinic_city` varchar(50) DEFAULT NULL,
  `clinic_state` varchar(50) DEFAULT NULL,
  `clinic_postal_code` varchar(20) DEFAULT NULL,
  `clinic_country` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `doctorcalendar`
--

CREATE TABLE `doctorcalendar` (
  `calendar_id` int(11) NOT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `is_available` tinyint(1) DEFAULT NULL,
  `break_start_time` time DEFAULT NULL,
  `break_end_time` time DEFAULT NULL,
  `appointment_duration` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL,
  `organizer_id` int(11) DEFAULT NULL,
  `event_title` varchar(255) DEFAULT NULL,
  `event_description` text DEFAULT NULL,
  `event_date` date DEFAULT NULL,
  `event_time` time DEFAULT NULL,
  `event_location` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `eventattendance`
--

CREATE TABLE `eventattendance` (
  `event_id` int(11) NOT NULL,
  `attendee_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `followup`
--

CREATE TABLE `followup` (
  `followup_id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `blood_pressure` float DEFAULT NULL,
  `heart_rate` int(11) DEFAULT NULL,
  `blood_sugar` float DEFAULT NULL,
  `followup_date` date DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `temperature` float DEFAULT NULL,
  `symptoms` varchar(255) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL,
  `notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `insurance`
--

CREATE TABLE `insurance` (
  `insurance_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `insurance_name` varchar(100) DEFAULT NULL,
  `address_line1` varchar(100) DEFAULT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `postal_code` varchar(20) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `laboratory`
--

CREATE TABLE `laboratory` (
  `laboratory_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `laboratory_name` varchar(100) DEFAULT NULL,
  `address_line1` varchar(100) DEFAULT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `postal_code` varchar(20) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `laboratoryanalysis`
--

CREATE TABLE `laboratoryanalysis` (
  `laboratory_id` int(11) NOT NULL,
  `analysis_id` int(11) NOT NULL,
  `price` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medicalhistory`
--

CREATE TABLE `medicalhistory` (
  `history_id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `prescription_id` int(11) DEFAULT NULL,
  `followup_id` int(11) DEFAULT NULL,
  `diagnosis` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medication`
--

CREATE TABLE `medication` (
  `medication_id` int(11) NOT NULL,
  `medication_name` varchar(100) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pharmacy`
--

CREATE TABLE `pharmacy` (
  `pharmacy_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `pharmacy_name` varchar(100) DEFAULT NULL,
  `address_line1` varchar(100) DEFAULT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `postal_code` varchar(20) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pharmacymedication`
--

CREATE TABLE `pharmacymedication` (
  `pharmacy_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `prescription_id` int(11) NOT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `prescription_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prescriptionmedication`
--

CREATE TABLE `prescriptionmedication` (
  `prescription_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  `dosage` varchar(50) DEFAULT NULL,
  `instructions` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `role` enum('doctor','patient','pharmacy','laboratory','insurance') DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `address_line1` varchar(100) DEFAULT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `postal_code` int(11) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `profile_picture_url` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `password`, `username`, `role`, `first_name`, `last_name`, `birthdate`, `address_line1`, `address_line2`, `city`, `state`, `postal_code`, `country`, `phone`, `profile_picture_url`) VALUES
(1, 'salimaroua1993@gmail.com', 'mypassword', 'SalimAroua', 'patient', 'Salim', 'Aroua', '2023-04-04', 'rue de russie', '', 'Bizerte', '', 7000, 'Tunisia', 53235426, 'test'),
(2, 'salimaroua1993@gmail.com', 'mypassword', 'SalimAroua', 'patient', 'Salim', 'Aroua', '1993-01-04', '52', 'rue de russie', 'Bizerte north', 'Bizerte', 7000, 'Tunisia', 53235426, 'test');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `analysis`
--
ALTER TABLE `analysis`
  ADD PRIMARY KEY (`analysis_id`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `calendar_id` (`calendar_id`);

--
-- Indexes for table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`consultation_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `patient_id` (`patient_id`);

--
-- Indexes for table `consultationanalysis`
--
ALTER TABLE `consultationanalysis`
  ADD PRIMARY KEY (`consultation_id`,`analysis_id`),
  ADD KEY `analysis_id` (`analysis_id`);

--
-- Indexes for table `consultationmedication`
--
ALTER TABLE `consultationmedication`
  ADD PRIMARY KEY (`consultation_id`,`medication_id`),
  ADD KEY `medication_id` (`medication_id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doctor_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `doctorcalendar`
--
ALTER TABLE `doctorcalendar`
  ADD PRIMARY KEY (`calendar_id`),
  ADD KEY `doctor_id` (`doctor_id`);

--
-- Indexes for table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `organizer_id` (`organizer_id`);

--
-- Indexes for table `eventattendance`
--
ALTER TABLE `eventattendance`
  ADD PRIMARY KEY (`event_id`,`attendee_id`),
  ADD KEY `attendee_id` (`attendee_id`);

--
-- Indexes for table `followup`
--
ALTER TABLE `followup`
  ADD PRIMARY KEY (`followup_id`),
  ADD KEY `patient_id` (`patient_id`);

--
-- Indexes for table `insurance`
--
ALTER TABLE `insurance`
  ADD PRIMARY KEY (`insurance_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `laboratory`
--
ALTER TABLE `laboratory`
  ADD PRIMARY KEY (`laboratory_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `laboratoryanalysis`
--
ALTER TABLE `laboratoryanalysis`
  ADD PRIMARY KEY (`laboratory_id`,`analysis_id`),
  ADD KEY `analysis_id` (`analysis_id`);

--
-- Indexes for table `medicalhistory`
--
ALTER TABLE `medicalhistory`
  ADD PRIMARY KEY (`history_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `prescription_id` (`prescription_id`),
  ADD KEY `followup_id` (`followup_id`);

--
-- Indexes for table `medication`
--
ALTER TABLE `medication`
  ADD PRIMARY KEY (`medication_id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patient_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD PRIMARY KEY (`pharmacy_id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `pharmacymedication`
--
ALTER TABLE `pharmacymedication`
  ADD PRIMARY KEY (`pharmacy_id`,`medication_id`),
  ADD KEY `medication_id` (`medication_id`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`prescription_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `patient_id` (`patient_id`);

--
-- Indexes for table `prescriptionmedication`
--
ALTER TABLE `prescriptionmedication`
  ADD PRIMARY KEY (`prescription_id`,`medication_id`),
  ADD KEY `medication_id` (`medication_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  ADD CONSTRAINT `appointment_ibfk_3` FOREIGN KEY (`calendar_id`) REFERENCES `doctorcalendar` (`calendar_id`);

--
-- Constraints for table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `consultation_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  ADD CONSTRAINT `consultation_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`);

--
-- Constraints for table `consultationanalysis`
--
ALTER TABLE `consultationanalysis`
  ADD CONSTRAINT `consultationanalysis_ibfk_1` FOREIGN KEY (`consultation_id`) REFERENCES `consultation` (`consultation_id`),
  ADD CONSTRAINT `consultationanalysis_ibfk_2` FOREIGN KEY (`analysis_id`) REFERENCES `analysis` (`analysis_id`);

--
-- Constraints for table `consultationmedication`
--
ALTER TABLE `consultationmedication`
  ADD CONSTRAINT `consultationmedication_ibfk_1` FOREIGN KEY (`consultation_id`) REFERENCES `consultation` (`consultation_id`),
  ADD CONSTRAINT `consultationmedication_ibfk_2` FOREIGN KEY (`medication_id`) REFERENCES `medication` (`medication_id`);

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `doctorcalendar`
--
ALTER TABLE `doctorcalendar`
  ADD CONSTRAINT `doctorcalendar_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`);

--
-- Constraints for table `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `event_ibfk_1` FOREIGN KEY (`organizer_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `eventattendance`
--
ALTER TABLE `eventattendance`
  ADD CONSTRAINT `eventattendance_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`),
  ADD CONSTRAINT `eventattendance_ibfk_2` FOREIGN KEY (`attendee_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `followup`
--
ALTER TABLE `followup`
  ADD CONSTRAINT `followup_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`);

--
-- Constraints for table `insurance`
--
ALTER TABLE `insurance`
  ADD CONSTRAINT `insurance_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `laboratory`
--
ALTER TABLE `laboratory`
  ADD CONSTRAINT `laboratory_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `laboratoryanalysis`
--
ALTER TABLE `laboratoryanalysis`
  ADD CONSTRAINT `laboratoryanalysis_ibfk_1` FOREIGN KEY (`laboratory_id`) REFERENCES `laboratory` (`laboratory_id`),
  ADD CONSTRAINT `laboratoryanalysis_ibfk_2` FOREIGN KEY (`analysis_id`) REFERENCES `analysis` (`analysis_id`);

--
-- Constraints for table `medicalhistory`
--
ALTER TABLE `medicalhistory`
  ADD CONSTRAINT `medicalhistory_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`),
  ADD CONSTRAINT `medicalhistory_ibfk_2` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`prescription_id`),
  ADD CONSTRAINT `medicalhistory_ibfk_3` FOREIGN KEY (`followup_id`) REFERENCES `followup` (`followup_id`);

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `pharmacy`
--
ALTER TABLE `pharmacy`
  ADD CONSTRAINT `pharmacy_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `pharmacymedication`
--
ALTER TABLE `pharmacymedication`
  ADD CONSTRAINT `pharmacymedication_ibfk_1` FOREIGN KEY (`pharmacy_id`) REFERENCES `pharmacy` (`pharmacy_id`),
  ADD CONSTRAINT `pharmacymedication_ibfk_2` FOREIGN KEY (`medication_id`) REFERENCES `medication` (`medication_id`);

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor` (`doctor_id`),
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`);

--
-- Constraints for table `prescriptionmedication`
--
ALTER TABLE `prescriptionmedication`
  ADD CONSTRAINT `prescriptionmedication_ibfk_1` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`prescription_id`),
  ADD CONSTRAINT `prescriptionmedication_ibfk_2` FOREIGN KEY (`medication_id`) REFERENCES `medication` (`medication_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
