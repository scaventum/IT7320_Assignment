-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 03, 2017 at 12:09 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_assignment1`
--

-- --------------------------------------------------------

--
-- Table structure for table `ba_stock`
--

CREATE TABLE IF NOT EXISTS `ba_stock` (
  `ItemID` varchar(50) NOT NULL,
  `TransactionNO` varchar(50) NOT NULL,
  `Qty` int(11) NOT NULL,
  PRIMARY KEY (`ItemID`,`TransactionNO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ba_stock`
--

INSERT INTO `ba_stock` (`ItemID`, `TransactionNO`, `Qty`) VALUES
('N00001', 'GR/2017/08/001', 1200),
('N00001', 'GR/2017/08/002', 300),
('N00002', 'GR/2017/08/001', 2000),
('N00002', 'GR/2017/08/002', 500);

-- --------------------------------------------------------

--
-- Table structure for table `ms_item`
--

CREATE TABLE IF NOT EXISTS `ms_item` (
  `ItemID` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `BuyPrice` int(4) NOT NULL,
  `SellPrice` int(4) NOT NULL,
  `UpdateTime` datetime NOT NULL,
  `UpdateUserID` varchar(50) NOT NULL,
  PRIMARY KEY (`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_item`
--

INSERT INTO `ms_item` (`ItemID`, `Name`, `BuyPrice`, `SellPrice`, `UpdateTime`, `UpdateUserID`) VALUES
('A00001', 'Adidas F50', 15, 40, '2017-07-29 17:56:37', 'ADM'),
('A00002', 'Adidas F1', 35, 55, '2017-07-29 17:57:09', 'ADM'),
('A00003', 'Adidas A5', 77, 190, '2017-07-29 17:58:29', 'ADM'),
('N00001', 'Nike Mercurial Spectre', 50, 105, '2017-07-29 17:58:50', 'ADM'),
('N00002', 'Nike Virtuoso Z', 35, 67, '2017-07-29 17:43:13', 'SCV'),
('R00001', 'Reebok United FF', 123, 323, '2017-07-29 18:00:35', 'ADM');

-- --------------------------------------------------------

--
-- Table structure for table `ms_partner`
--

CREATE TABLE IF NOT EXISTS `ms_partner` (
  `PartnerID` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `UpdateTime` datetime NOT NULL,
  `UpdateUserID` varchar(50) NOT NULL,
  PRIMARY KEY (`PartnerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_partner`
--

INSERT INTO `ms_partner` (`PartnerID`, `Name`, `Address`, `UpdateTime`, `UpdateUserID`) VALUES
('A001', 'Adidas NZ', 'Porirua, Wellington Region', '2017-07-29 18:32:07', 'ADM'),
('N001', 'Nike AUNZ', '1st Queens Street, Auckland, Auckland Region', '2017-07-29 18:34:59', 'ADM');

-- --------------------------------------------------------

--
-- Table structure for table `ms_user`
--

CREATE TABLE IF NOT EXISTS `ms_user` (
  `UserID` varchar(50) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `UpdateTime` datetime NOT NULL,
  `UpdateUserID` varchar(50) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ms_user`
--

INSERT INTO `ms_user` (`UserID`, `Name`, `Password`, `UpdateTime`, `UpdateUserID`) VALUES
('ADM', 'Administrator', '12345', '2017-07-25 21:08:00', 'ADM'),
('AYD', 'Mbok Ayudya', '12345', '2017-07-29 16:53:48', 'ADM'),
('AYI', 'Febrina Amelia', '12345', '2017-07-26 20:58:44', 'ADM'),
('GPS', 'Gurpreet', '12345', '2017-07-25 21:34:21', 'SCV'),
('GUE', 'Guess', '12345', '2017-07-25 21:35:09', 'SCV'),
('HSN', 'Hanson Budiman', '12345', '2017-07-25 21:38:53', 'ZZZ'),
('LP1', 'Chester Bennington', '12345', '2017-07-25 21:36:53', 'ZZZ'),
('LP2', 'Mike Shinoda', '12345', '2017-07-25 21:37:11', 'ZZZ'),
('LP3', 'Brad Delson', '12345', '2017-07-25 21:37:23', 'ZZZ'),
('MTH', 'Mas Mustolih', '212', '2017-07-29 16:53:35', 'ADM'),
('NJG', 'Navoda', '12345', '2017-07-25 21:34:10', 'SCV'),
('PIK', 'Pikachu', '12345', '2017-07-25 21:36:36', 'ZZZ'),
('SCV', 'Ryan Scv', '12345', '2017-07-24 21:21:46', 'ADM'),
('STA', 'Staff ', '12345', '2017-07-25 21:38:07', 'ZZZ'),
('ZZZ', 'Snorlax', 'zzz', '2017-07-25 21:08:10', 'ADM');

-- --------------------------------------------------------

--
-- Table structure for table `tr_do_dt`
--

CREATE TABLE IF NOT EXISTS `tr_do_dt` (
  `DONO` varchar(50) NOT NULL,
  `ItemID` varchar(50) NOT NULL,
  `Qty` int(11) NOT NULL,
  PRIMARY KEY (`DONO`,`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tr_do_hd`
--

CREATE TABLE IF NOT EXISTS `tr_do_hd` (
  `DONO` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `SONO` varchar(50) NOT NULL,
  `Address` varchar(200) NOT NULL,
  `Updatetime` datetime NOT NULL,
  `UpdateUserID` varchar(50) NOT NULL,
  PRIMARY KEY (`DONO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tr_gr_dt`
--

CREATE TABLE IF NOT EXISTS `tr_gr_dt` (
  `GRNO` varchar(50) NOT NULL,
  `ItemID` varchar(50) NOT NULL,
  `Qty` int(11) NOT NULL,
  PRIMARY KEY (`GRNO`,`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tr_gr_dt`
--

INSERT INTO `tr_gr_dt` (`GRNO`, `ItemID`, `Qty`) VALUES
('GR/2017/08/001', 'N00001', 1200),
('GR/2017/08/001', 'N00002', 2000),
('GR/2017/08/002', 'N00001', 300),
('GR/2017/08/002', 'N00002', 500);

-- --------------------------------------------------------

--
-- Table structure for table `tr_gr_hd`
--

CREATE TABLE IF NOT EXISTS `tr_gr_hd` (
  `GRNO` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `PONO` varchar(50) NOT NULL,
  `UpdateTime` datetime NOT NULL,
  `UpdateUserID` varchar(50) NOT NULL,
  PRIMARY KEY (`GRNO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tr_gr_hd`
--

INSERT INTO `tr_gr_hd` (`GRNO`, `Date`, `PONO`, `UpdateTime`, `UpdateUserID`) VALUES
('GR/2017/08/001', '2017-08-03', 'PO/2017/07/005', '2017-08-03 21:57:23', 'ADM'),
('GR/2017/08/002', '2017-08-03', 'PO/2017/07/005', '2017-08-03 21:57:40', 'ADM');

-- --------------------------------------------------------

--
-- Table structure for table `tr_po_dt`
--

CREATE TABLE IF NOT EXISTS `tr_po_dt` (
  `PONO` varchar(50) NOT NULL,
  `ItemID` varchar(50) NOT NULL,
  `RetailPrice` int(11) NOT NULL,
  `Qty` int(11) NOT NULL,
  PRIMARY KEY (`PONO`,`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tr_po_dt`
--

INSERT INTO `tr_po_dt` (`PONO`, `ItemID`, `RetailPrice`, `Qty`) VALUES
('PO/2017/07/002', 'A00001', 15, 3000),
('PO/2017/07/002', 'A00002', 35, 115),
('PO/2017/07/004', 'N00001', 51, 100),
('PO/2017/07/005', 'N00001', 39, 1500),
('PO/2017/07/005', 'N00002', 30, 2500),
('PO/2017/07/006', 'N00002', 35, 10000),
('PO/2017/08/001', 'N00002', 35, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `tr_po_hd`
--

CREATE TABLE IF NOT EXISTS `tr_po_hd` (
  `PONO` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `SupplierID` varchar(50) NOT NULL,
  `UpdateTime` datetime NOT NULL,
  `UpdateUserID` varchar(50) NOT NULL,
  PRIMARY KEY (`PONO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tr_po_hd`
--

INSERT INTO `tr_po_hd` (`PONO`, `Date`, `SupplierID`, `UpdateTime`, `UpdateUserID`) VALUES
('PO/2017/07/002', '2017-07-30', 'A001', '2017-07-30 20:34:18', 'ADM'),
('PO/2017/07/004', '2017-07-30', 'N001', '2017-07-30 17:35:15', 'ADM'),
('PO/2017/07/005', '2017-07-30', 'N001', '2017-07-30 18:23:23', 'MTH'),
('PO/2017/07/006', '2017-07-31', 'N001', '2017-07-31 11:55:59', 'ADM'),
('PO/2017/08/001', '2017-08-03', 'N001', '2017-08-03 13:00:47', 'ADM');

-- --------------------------------------------------------

--
-- Table structure for table `tr_so_dt`
--

CREATE TABLE IF NOT EXISTS `tr_so_dt` (
  `SONO` varchar(50) NOT NULL,
  `ItemID` varchar(50) NOT NULL,
  `RetailPrice` int(11) NOT NULL,
  `Qty` int(11) NOT NULL,
  PRIMARY KEY (`SONO`,`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tr_so_hd`
--

CREATE TABLE IF NOT EXISTS `tr_so_hd` (
  `SONO` varchar(50) NOT NULL,
  `Date` date NOT NULL,
  `CustomerID` varchar(50) NOT NULL,
  `Updatetime` datetime NOT NULL,
  `UpdateUserID` varchar(50) NOT NULL,
  PRIMARY KEY (`SONO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
