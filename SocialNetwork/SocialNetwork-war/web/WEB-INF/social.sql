-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 17, 2014 at 05:18 AM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `social`
--
DROP DATABASE IF EXISTS `social`;
CREATE DATABASE IF NOT EXISTS `social`;
USE `social`;

-- --------------------------------------------------------
--
-- Table structure for table `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(60) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Phone` varchar(16) COLLATE utf8_unicode_ci NOT NULL,
  `DOB` varchar(12) COLLATE utf8_unicode_ci NOT NULL,
  `ActivationStatus` int(11) NOT NULL,
  `Password` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`UserId`,`Email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=7 ;


-- --------------------------------------------------------

--
-- Table structure for table `tbl_status`
--

CREATE TABLE IF NOT EXISTS `tbl_status` (
  `StatusId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `Title` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Status` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `PostDate` varchar(25) NOT NULL,
  PRIMARY KEY (`StatusId`)
  
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_comment`
--

CREATE TABLE IF NOT EXISTS `tbl_comment` (
  `CommentId` int(11) NOT NULL AUTO_INCREMENT,
  `StatusId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `Comment` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `CommentedDate` varchar(25) NOT NULL,
  PRIMARY KEY (`CommentId`)
  
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

-- 
-- ADD Foreign Key
--

ALTER TABLE `tbl_status`
        ADD CONSTRAINT `fk_UserId` FOREIGN KEY(`UserId`) REFERENCES `tbl_user`(`UserId`) ON DELETE CASCADE;

ALTER TABLE `tbl_comment`
        ADD CONSTRAINT `foreign_UserId` FOREIGN KEY(`UserId`) REFERENCES `tbl_user`(`UserId`) ON DELETE CASCADE,
        ADD CONSTRAINT `fk_StatusId` FOREIGN KEY(`StatusId`) REFERENCES `tbl_status`(`StatusId`) ON DELETE CASCADE;
        

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
