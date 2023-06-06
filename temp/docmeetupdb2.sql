-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2023 at 12:17 AM
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
-- Database: `docmeetupdb2.0`
--

-- --------------------------------------------------------

--
-- Table structure for table `analysis`
--

CREATE TABLE `analysis` (
  `analysis_id` int(11) NOT NULL,
  `analysis_name` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `result_type` varchar(255) DEFAULT NULL
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
  `calendar_id` int(11) DEFAULT NULL,
  `status` enum('scheduled','cancelled','completed') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `calendar`
--

CREATE TABLE `calendar` (
  `calendar_id` int(11) NOT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `day_of_week` int(11) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `is_break` tinyint(1) DEFAULT NULL,
  `break_start_time` time DEFAULT NULL,
  `break_end_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `consultation`
--

CREATE TABLE `consultation` (
  `consultation_id` int(11) NOT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `followup_id` int(11) DEFAULT NULL,
  `medicalfile_id` int(11) DEFAULT NULL,
  `consultation_date` date DEFAULT NULL,
  `consultation_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `event`
--

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL,
  `organizer_id` int(11) DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `event_date` date DEFAULT NULL,
  `event_time` time DEFAULT NULL
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
  `user_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `blood_pressure` varchar(10) DEFAULT NULL,
  `heart_rate` int(11) DEFAULT NULL,
  `temperature` decimal(5,2) DEFAULT NULL,
  `weight` decimal(5,2) DEFAULT NULL,
  `symptoms` text DEFAULT NULL,
  `additional_notes` text DEFAULT NULL,
  `diagnosis` text DEFAULT NULL,
  `prescribed_medication` text DEFAULT NULL,
  `recommended_tests` text DEFAULT NULL,
  `next_appointment_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `insurancefile`
--

CREATE TABLE `insurancefile` (
  `file_id` int(11) NOT NULL,
  `insurance_id` int(11) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `medicalfile_id` int(11) DEFAULT NULL,
  `insurance_name` varchar(255) DEFAULT NULL,
  `insurance_policy_number` varchar(255) DEFAULT NULL,
  `insurance_details` text DEFAULT NULL,
  `insurance_start_date` date DEFAULT NULL,
  `insurance_end_date` date DEFAULT NULL,
  `insurance_coverage` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medicalfile`
--

CREATE TABLE `medicalfile` (
  `file_id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `followup_id` int(11) DEFAULT NULL,
  `medical_info` text DEFAULT NULL,
  `medical_history` text DEFAULT NULL,
  `last_visit_date` date DEFAULT NULL,
  `next_visit_date` date DEFAULT NULL,
  `allergies` text DEFAULT NULL,
  `current_medications` text DEFAULT NULL,
  `previous_conditions` text DEFAULT NULL,
  `surgeries` text DEFAULT NULL,
  `lab_results` text DEFAULT NULL,
  `imaging_results` text DEFAULT NULL,
  `prescribed_medications` text DEFAULT NULL,
  `prescribed_analysis` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medication`
--

CREATE TABLE `medication` (
  `medication_id` int(11) NOT NULL,
  `medication_name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `dosage_form` varchar(100) DEFAULT NULL,
  `strength` varchar(100) DEFAULT NULL,
  `route_of_administration` varchar(100) DEFAULT NULL,
  `manufacturer` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `active_substances` varchar(255) DEFAULT NULL,
  `warnings` varchar(255) DEFAULT NULL,
  `side_effects` varchar(255) DEFAULT NULL,
  `storage_conditions` varchar(255) DEFAULT NULL,
  `additional_information` text DEFAULT NULL
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
-- Table structure for table `prescriptionanalysis`
--

CREATE TABLE `prescriptionanalysis` (
  `prescription_id` int(11) NOT NULL,
  `analysis_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prescriptionmedication`
--

CREATE TABLE `prescriptionmedication` (
  `prescription_id` int(11) NOT NULL,
  `medication_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `postal_code` int(11) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `phone` int(11) DEFAULT NULL,
  `profile_picture_url` varchar(255) DEFAULT NULL,
  `tax_registration_number` varchar(255) DEFAULT NULL,
  `role` enum('doctor','patient','pharmacy','laboratory','insurance') DEFAULT NULL,
  `doctor_specialty` enum('generaliste','dentiste',' pédiatrie','gynécologie','dermatologie','neurologie','ophtalmologie','rhumatologie') DEFAULT NULL,
  `doctor_license_number` varchar(255) DEFAULT NULL,
  `doctor_clinic_name` varchar(255) DEFAULT NULL,
  `insurance_name` varchar(255) DEFAULT NULL,
  `laboratory_name` varchar(255) DEFAULT NULL,
  `pharmacy_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `analysis`
--
ALTER TABLE `analysis`
  ADD PRIMARY KEY (`analysis_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`appointment_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `calendar_id` (`calendar_id`);

--
-- Indexes for table `calendar`
--
ALTER TABLE `calendar`
  ADD PRIMARY KEY (`calendar_id`),
  ADD KEY `doctor_id` (`doctor_id`);

--
-- Indexes for table `consultation`
--
ALTER TABLE `consultation`
  ADD PRIMARY KEY (`consultation_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `followup_id` (`followup_id`),
  ADD KEY `medicalfile_id` (`medicalfile_id`);

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
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `insurancefile`
--
ALTER TABLE `insurancefile`
  ADD PRIMARY KEY (`file_id`),
  ADD KEY `medicalfile_id` (`medicalfile_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `insurance_id` (`insurance_id`);

--
-- Indexes for table `medicalfile`
--
ALTER TABLE `medicalfile`
  ADD PRIMARY KEY (`file_id`),
  ADD KEY `patient_id` (`patient_id`),
  ADD KEY `followup_id` (`followup_id`);

--
-- Indexes for table `medication`
--
ALTER TABLE `medication`
  ADD PRIMARY KEY (`medication_id`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`prescription_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `patient_id` (`patient_id`);

--
-- Indexes for table `prescriptionanalysis`
--
ALTER TABLE `prescriptionanalysis`
  ADD PRIMARY KEY (`prescription_id`,`analysis_id`),
  ADD KEY `analysis_id` (`analysis_id`);

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
-- Constraints for dumped tables
--

--
-- Constraints for table `analysis`
--
ALTER TABLE `analysis`
  ADD CONSTRAINT `analysis_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `appointment_ibfk_3` FOREIGN KEY (`calendar_id`) REFERENCES `calendar` (`calendar_id`);

--
-- Constraints for table `calendar`
--
ALTER TABLE `calendar`
  ADD CONSTRAINT `calendar_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `consultation`
--
ALTER TABLE `consultation`
  ADD CONSTRAINT `consultation_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `consultation_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `consultation_ibfk_3` FOREIGN KEY (`followup_id`) REFERENCES `followup` (`followup_id`),
  ADD CONSTRAINT `consultation_ibfk_4` FOREIGN KEY (`medicalfile_id`) REFERENCES `medicalfile` (`file_id`);

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
  ADD CONSTRAINT `followup_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `insurancefile`
--
ALTER TABLE `insurancefile`
  ADD CONSTRAINT `insurancefile_ibfk_1` FOREIGN KEY (`medicalfile_id`) REFERENCES `medicalfile` (`file_id`),
  ADD CONSTRAINT `insurancefile_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `insurancefile_ibfk_3` FOREIGN KEY (`insurance_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `medicalfile`
--
ALTER TABLE `medicalfile`
  ADD CONSTRAINT `medicalfile_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `medicalfile_ibfk_2` FOREIGN KEY (`followup_id`) REFERENCES `followup` (`followup_id`);

--
-- Constraints for table `prescription`
--
ALTER TABLE `prescription`
  ADD CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `user` (`user_id`);

--
-- Constraints for table `prescriptionanalysis`
--
ALTER TABLE `prescriptionanalysis`
  ADD CONSTRAINT `prescriptionanalysis_ibfk_1` FOREIGN KEY (`prescription_id`) REFERENCES `prescription` (`prescription_id`),
  ADD CONSTRAINT `prescriptionanalysis_ibfk_2` FOREIGN KEY (`analysis_id`) REFERENCES `analysis` (`analysis_id`);

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
