-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-04-2016 a las 04:11:30
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `prueba`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entregables`
--

CREATE TABLE IF NOT EXISTS `entregables` (
  `idFase` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fase`
--

CREATE TABLE IF NOT EXISTS `fase` (
`id` int(11) NOT NULL,
  `idProyecto` varchar(20) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `fase`
--

INSERT INTO `fase` (`id`, `idProyecto`, `nombre`, `fechaInicio`, `fechaFin`) VALUES
(1, '3211', 'Primera fase', '2016-04-12', '2017-04-12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

CREATE TABLE IF NOT EXISTS `proyecto` (
  `id` varchar(20) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `objetivo` text NOT NULL,
  `alcance` text NOT NULL,
  `tiempoEstimado` varchar(20) NOT NULL,
  `area` varchar(30) NOT NULL,
  `costo` bigint(20) NOT NULL,
  `estado` enum('propuestos','ejecucion','terminado') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proyecto`
--

INSERT INTO `proyecto` (`id`, `nombre`, `objetivo`, `alcance`, `tiempoEstimado`, `area`, `costo`, `estado`) VALUES
('2222', 'PIZA', 'Gestion de hojas de vida', 'Nivel Mundial', '3 años', 'Tic', 9000000, 'propuestos'),
('3211', 'MECAUT', 'Proyecto de gestion automotriz', 'A todo el sector industrial de mecanica automotriz', '1 año', 'Industrial', 4500000, 'propuestos'),
('3457', 'ANIBOL', 'Gestion de pagos', 'Nacional', '2 años', 'Industrial', 1000000, 'propuestos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `responsables`
--

CREATE TABLE IF NOT EXISTS `responsables` (
  `idFase` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `rol` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `entregables`
--
ALTER TABLE `entregables`
 ADD KEY `idFase` (`idFase`);

--
-- Indices de la tabla `fase`
--
ALTER TABLE `fase`
 ADD PRIMARY KEY (`id`), ADD KEY `idProyecto` (`idProyecto`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `responsables`
--
ALTER TABLE `responsables`
 ADD KEY `idFase` (`idFase`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `fase`
--
ALTER TABLE `fase`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entregables`
--
ALTER TABLE `entregables`
ADD CONSTRAINT `entregables_ibfk_1` FOREIGN KEY (`idFase`) REFERENCES `fase` (`id`);

--
-- Filtros para la tabla `fase`
--
ALTER TABLE `fase`
ADD CONSTRAINT `fase_ibfk_1` FOREIGN KEY (`idProyecto`) REFERENCES `proyecto` (`id`);

--
-- Filtros para la tabla `responsables`
--
ALTER TABLE `responsables`
ADD CONSTRAINT `responsables_ibfk_1` FOREIGN KEY (`idFase`) REFERENCES `fase` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
