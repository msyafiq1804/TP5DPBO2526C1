-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 16 Okt 2026 pada 17.24
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_people`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien`
--

CREATE TABLE `pasien` (
  `id` varchar(50) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `tahun_lahir` int(11) DEFAULT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `status_rawat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pasien`
--

INSERT INTO `pasien` (`id`, `nama`, `tahun_lahir`, `kategori`, `status_rawat`) VALUES
('PSN001', 'Wowo Dodo', 1990, 'BPJS', 'Rawat Jalan'),
('PSN002', 'Siti Aisyah', 1985, 'Umum', 'Rawat Inap'),
('PSN003', 'Andi Purnomo', 1992, 'Asuransi', 'Rawat Jalan'),
('PSN004', 'Cristiano Ronaldo', 1988, 'BPJS', 'Rawat Inap'),
('PSN005', 'Bambang Pamungkas', 1975, 'Umum', 'Rawat Jalan'),
('PSN006', 'Eli Lestari', 1995, 'BPJS', 'Rawat Inap'),
('PSN007', 'Fajar Setiawan', 2001, 'Asuransi', 'Rawat Jalan'),
('PSN008', 'Sari Indah', 1982, 'Umum', 'Rawat Inap');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
