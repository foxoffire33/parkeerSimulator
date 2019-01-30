-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Gegenereerd op: 30 jan 2019 om 22:37
-- Serverversie: 10.1.36-MariaDB
-- PHP-versie: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `cars`
--

CREATE TABLE `cars` (
  `id` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL,
  `numberplate` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `updated_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Gegevens worden geëxporteerd voor tabel `cars`
--

INSERT INTO `cars` (`id`, `user_id`, `numberplate`, `created_at`, `updated_at`) VALUES
(1, 1, 'kkkkkk', '2019-01-14 18:27:58', '0000-00-00 00:00:00'),
(2, 1, 'ksmksdmksdm;lsd', '2019-01-14 17:34:18', '2019-01-14 17:34:18'),
(3, 1, 'hoi', '2019-01-14 17:34:34', '2019-01-14 17:34:34');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Gegevens worden geëxporteerd voor tabel `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(4, '2019_01_14_145214_cars', 2),
(5, '2019_01_14_170002_create_mission_reports_table', 3),
(6, '2019_01_14_191218_create_reservations_table', 4);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `mission_reports`
--

CREATE TABLE `mission_reports` (
  `id` int(10) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `token` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `reservations`
--

CREATE TABLE `reservations` (
  `id` int(10) UNSIGNED NOT NULL,
  `car_id` int(10) UNSIGNED NOT NULL,
  `from_time` time NOT NULL,
  `to_time` time NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Gegevens worden geëxporteerd voor tabel `reservations`
--

INSERT INTO `reservations` (`id`, `car_id`, `from_time`, `to_time`, `created_at`, `updated_at`) VALUES
(17, 2, '12:30:00', '21:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(18, 1, '14:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(19, 2, '12:30:00', '20:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(20, 1, '12:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(21, 2, '12:30:00', '21:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(22, 1, '14:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(23, 2, '12:30:00', '20:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(24, 1, '12:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(25, 2, '12:30:00', '21:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(26, 1, '14:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(27, 2, '12:30:00', '20:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(28, 1, '12:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(29, 2, '12:30:00', '21:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(30, 1, '14:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(31, 2, '12:30:00', '20:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(32, 1, '12:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(33, 2, '12:30:00', '21:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(34, 1, '14:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(35, 2, '12:30:00', '20:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(36, 1, '12:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(37, 2, '12:30:00', '21:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(38, 1, '14:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(39, 2, '12:30:00', '20:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(40, 1, '12:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(41, 2, '12:30:00', '21:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(42, 1, '14:30:00', '16:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43'),
(43, 2, '12:30:00', '20:00:00', '2019-01-14 20:37:43', '2019-01-14 20:37:43');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(191) COLLATE utf8mb4_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Gegevens worden geëxporteerd voor tabel `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `email_verified_at`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'Reinier de la Parra', 'reinierdlp@gmail.com', NULL, '$2y$10$CG4h3yQS3dIIR52KxbhNMu.Oz3vXCbfV4Gc9/hOJrUMsDv.lABK5C', 'sogyptAchj9S22WVEeBhxP25fSBZuqqAlPZrAE4hNRgwAJaNJBUEv6RElvjC', '2019-01-14 13:50:48', '2019-01-14 13:50:48');

--
-- Indexen voor geëxporteerde tabellen
--

--
-- Indexen voor tabel `cars`
--
ALTER TABLE `cars`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cars_user_id_foreign` (`user_id`);

--
-- Indexen voor tabel `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `mission_reports`
--
ALTER TABLE `mission_reports`
  ADD PRIMARY KEY (`id`);

--
-- Indexen voor tabel `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexen voor tabel `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `reservations_car_id_foreign` (`car_id`);

--
-- Indexen voor tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT voor geëxporteerde tabellen
--

--
-- AUTO_INCREMENT voor een tabel `cars`
--
ALTER TABLE `cars`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT voor een tabel `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT voor een tabel `mission_reports`
--
ALTER TABLE `mission_reports`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT voor een tabel `reservations`
--
ALTER TABLE `reservations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT voor een tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Beperkingen voor geëxporteerde tabellen
--

--
-- Beperkingen voor tabel `cars`
--
ALTER TABLE `cars`
  ADD CONSTRAINT `cars_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Beperkingen voor tabel `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_car_id_foreign` FOREIGN KEY (`car_id`) REFERENCES `cars` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
