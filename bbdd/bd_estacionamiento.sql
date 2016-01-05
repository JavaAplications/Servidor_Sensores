-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-01-2016 a las 19:35:09
-- Versión del servidor: 10.1.9-MariaDB
-- Versión de PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_estacionamiento`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carteles`
--

CREATE TABLE `carteles` (
  `ID_Cartel` int(15) UNSIGNED NOT NULL,
  `IP_Cartel` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `carteles`
--

INSERT INTO `carteles` (`ID_Cartel`, `IP_Cartel`) VALUES
(1, '127.0.0.1'),
(2, '192.168.0.100');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `eventos`
--

CREATE TABLE `eventos` (
  `ID_Evento` int(15) UNSIGNED NOT NULL,
  `ID_Sensor` int(15) UNSIGNED NOT NULL,
  `Horario` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `Estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `eventos`
--

INSERT INTO `eventos` (`ID_Evento`, `ID_Sensor`, `Horario`, `Estado`) VALUES
(1, 1, '2016-01-05 15:56:32', 0),
(2, 1, '2016-01-05 15:56:41', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sensores`
--

CREATE TABLE `sensores` (
  `ID_Sensor` int(15) UNSIGNED NOT NULL,
  `IP_Sensor` varchar(30) NOT NULL,
  `ID_Cartel` int(15) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabla Sensores';

--
-- Volcado de datos para la tabla `sensores`
--

INSERT INTO `sensores` (`ID_Sensor`, `IP_Sensor`, `ID_Cartel`) VALUES
(1, '127.0.0.1', 1),
(2, '192.168.0.100', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `status_sensor`
--

CREATE TABLE `status_sensor` (
  `ID_Sensor` int(15) UNSIGNED NOT NULL,
  `Estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carteles`
--
ALTER TABLE `carteles`
  ADD PRIMARY KEY (`ID_Cartel`);

--
-- Indices de la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD PRIMARY KEY (`ID_Evento`),
  ADD KEY `ID_Sensor` (`ID_Sensor`);

--
-- Indices de la tabla `sensores`
--
ALTER TABLE `sensores`
  ADD PRIMARY KEY (`ID_Sensor`),
  ADD KEY `ID_Cartel` (`ID_Cartel`);

--
-- Indices de la tabla `status_sensor`
--
ALTER TABLE `status_sensor`
  ADD PRIMARY KEY (`ID_Sensor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `carteles`
--
ALTER TABLE `carteles`
  MODIFY `ID_Cartel` int(15) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `eventos`
--
ALTER TABLE `eventos`
  MODIFY `ID_Evento` int(15) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `sensores`
--
ALTER TABLE `sensores`
  MODIFY `ID_Sensor` int(15) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `eventos`
--
ALTER TABLE `eventos`
  ADD CONSTRAINT `eventos_ibfk_1` FOREIGN KEY (`ID_Sensor`) REFERENCES `sensores` (`ID_Sensor`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `sensores`
--
ALTER TABLE `sensores`
  ADD CONSTRAINT `sensores_ibfk_1` FOREIGN KEY (`ID_Cartel`) REFERENCES `carteles` (`ID_Cartel`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `status_sensor`
--
ALTER TABLE `status_sensor`
  ADD CONSTRAINT `status_sensor_ibfk_1` FOREIGN KEY (`ID_Sensor`) REFERENCES `sensores` (`ID_Sensor`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
