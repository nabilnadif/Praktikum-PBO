-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 09, 2025 at 03:32 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `proyekpbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `jumlah_stok` int(11) NOT NULL,
  `id_kategori` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `harga`, `jumlah_stok`, `id_kategori`) VALUES
(1, 'CPU', 8000000.00, 10, 1),
(2, 'GPU', 4000000.00, 10, 1),
(3, 'Motherboard', 1000000.00, 10, 1),
(4, 'Casing PC', 500000.00, 10, 1),
(5, 'Power Supply', 800000.00, 10, 1),
(6, 'ASUS TUF', 18000000.00, 10, 2),
(7, 'MSI', 12000000.00, 10, 2),
(8, 'Macbook Air', 25000000.00, 10, 2),
(9, 'Lenovo', 9000000.00, 10, 2),
(10, 'HP', 8000000.00, 10, 2),
(11, 'Monitor', 1500000.00, 15, 3),
(12, 'Mouse', 200000.00, 15, 3),
(13, 'Keyboard', 400000.00, 15, 3),
(14, 'Headset', 300000.00, 15, 3),
(15, 'Meja Komputer', 600000.00, 15, 3);

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`) VALUES
(3, 'gaming_gear'),
(1, 'komponen_pc'),
(2, 'laptop');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
-- (Ditambahkan untuk keperluan Login.java dan Supplier.java)
--

CREATE TABLE `supplier` (
  `id_supplier` varchar(50) NOT NULL,
  `nama_supplier` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_supplier`) VALUES
('S001', 'PT. PC'),
('S002', 'PT. Laptop');

-- --------------------------------------------------------

--
-- Table structure for table `cashier`
-- (Ditambahkan untuk keperluan Login.java dan Cashier.java)
--

CREATE TABLE `cashier` (
  `id_cashier` varchar(50) NOT NULL,
  `nama_cashier` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cashier`
--

INSERT INTO `cashier` (`id_cashier`, `nama_cashier`) VALUES
('C001', 'Nabil'),
('C002', 'Raka'),
('C003', 'Khumaira');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_masuk`
-- (Ditambahkan untuk keperluan Supplier.java)
--

CREATE TABLE `transaksi_masuk` (
  `id_transaksiMasuk` int(11) NOT NULL,
  `tgl_transaksi` varchar(20) DEFAULT NULL,
  `jumlah_masuk` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `id_supplier` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_keluar`
-- (Ditambahkan untuk keperluan Cashier.java)
--

CREATE TABLE `transaksi_keluar` (
  `id_transaksiKeluar` varchar(50) NOT NULL,
  `tgl_transaksi` varchar(20) DEFAULT NULL,
  `jumlah_keluar` int(11) NOT NULL,
  `harga_jual` decimal(15,2) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `id_cashier` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `id_kategori` (`id_kategori`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`),
  ADD UNIQUE KEY `nama_kategori` (`nama_kategori`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indexes for table `cashier`
--
ALTER TABLE `cashier`
  ADD PRIMARY KEY (`id_cashier`);

--
-- Indexes for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD PRIMARY KEY (`id_transaksiMasuk`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Indexes for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD PRIMARY KEY (`id_transaksiKeluar`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_cashier` (`id_cashier`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`);

--
-- Constraints for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD CONSTRAINT `fk_tm_barang` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`),
  ADD CONSTRAINT `fk_tm_supplier` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`);

--
-- Constraints for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD CONSTRAINT `fk_tk_barang` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`),
  ADD CONSTRAINT `fk_tk_cashier` FOREIGN KEY (`id_cashier`) REFERENCES `cashier` (`id_cashier`);

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;