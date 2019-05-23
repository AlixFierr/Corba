-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2019 a las 02:08:56
-- Versión del servidor: 10.1.9-MariaDB
-- Versión de PHP: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `huellas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asistencia_personal`
--

CREATE TABLE `asistencia_personal` (
  `idasistencia` int(11) NOT NULL,
  `idpersonal` int(11) NOT NULL,
  `identificacion` int(11) NOT NULL,
  `fecha` varchar(50) NOT NULL,
  `hora_ingreso` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `asistencia_personal`
--

INSERT INTO `asistencia_personal` (`idasistencia`, `idpersonal`, `identificacion`, `fecha`, `hora_ingreso`) VALUES
(1, 1, 1234567890, '2019/05/14', '05:00:18'),
(2, 1, 1234567890, '2019/05/14', '05:29:30'),
(3, 1, 1234567890, '2019/05/14', '05:29:50'),
(4, 1, 1234567890, '2019/05/14', '05:50:15'),
(5, 1, 1234567890, '2019/05/20', '01:29:30'),
(6, 1, 1234567890, '2019/05/18', '05:40:35');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cargo`
--

CREATE TABLE `cargo` (
  `idcargo` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cargo`
--

INSERT INTO `cargo` (`idcargo`, `descripcion`) VALUES
(1, 'Directoria'),
(2, 'Rectoria'),
(3, 'Enfermeria');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `identificacion` int(11) NOT NULL,
  `elunes` varchar(50) NOT NULL,
  `emartes` varchar(50) NOT NULL,
  `emiercoles` varchar(50) NOT NULL,
  `ejueves` varchar(50) NOT NULL,
  `eviernes` varchar(50) NOT NULL,
  `slunes` varchar(50) NOT NULL,
  `smartes` varchar(50) NOT NULL,
  `smiercoles` varchar(50) NOT NULL,
  `sjueves` varchar(50) NOT NULL,
  `sviernes` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`identificacion`, `elunes`, `emartes`, `emiercoles`, `ejueves`, `eviernes`, `slunes`, `smartes`, `smiercoles`, `sjueves`, `sviernes`) VALUES
(1234567890, '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00'),
(1234567890, '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00'),
(1079179829, '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00', '07:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `idpermiso` int(11) NOT NULL,
  `identificacion` int(11) NOT NULL,
  `fecha` varchar(50) NOT NULL,
  `tiempo` varchar(50) NOT NULL,
  `tipo_permiso` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`idpermiso`, `identificacion`, `fecha`, `tiempo`, `tipo_permiso`, `descripcion`) VALUES
(1, 1234567890, '2019/05/15', '30 s', 'ausencia', 'baño'),
(2, 1075291694, '2019/05/09', 'completo', 'personal', 'Familiar');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `idpersonal` int(11) NOT NULL,
  `identificacion` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`idpersonal`, `identificacion`, `nombre`, `apellido`, `direccion`, `telefono`, `email`) VALUES
(1, 1234567890, 'Daniel', 'Arevalo', 'Casa', '1234567890', 'diarevalo@corhuila.edu.co'),
(3, 1079179829, 'Delman', 'Suarez', 'Calle 0 N 1 01', '3204765005', 'delman92as@gmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asistencia_personal`
--
ALTER TABLE `asistencia_personal`
  ADD PRIMARY KEY (`idasistencia`);

--
-- Indices de la tabla `cargo`
--
ALTER TABLE `cargo`
  ADD PRIMARY KEY (`idcargo`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`idpermiso`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`idpersonal`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asistencia_personal`
--
ALTER TABLE `asistencia_personal`
  MODIFY `idasistencia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `cargo`
--
ALTER TABLE `cargo`
  MODIFY `idcargo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `idpermiso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `personal`
--
ALTER TABLE `personal`
  MODIFY `idpersonal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
