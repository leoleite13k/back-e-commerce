-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 10-Maio-2020 às 22:42
-- Versão do servidor: 10.4.11-MariaDB
-- versão do PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `lixo`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE `produto` (
  `id` int(11) NOT NULL,
  `nome` varchar(120) NOT NULL,
  `preco` float(11) NOT NULL,
  `foto` varchar(254) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `descricao` varchar(254),
  `promocao` boolean
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `produto`
--

INSERT INTO `produto` (`nome`, `preco`, `foto`, `quantidade`, `descricao`, `promocao`) VALUES
(1, 'Teclado Mecânico Razer', 170.00, 'image', 200, 'Teclado Gamer com RBG', true),
(2, 'Mouse Logitech', 99.90, 'image', 1000, 'Mouse Logitech M90', false),
(3, 'Monitor Benq', 607.10, 'image', 10, 'Monitor 4k com 75Hz',true);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL
  `email` varchar(154) NOT NULL
  `password` varchar(100) NOT NULL
  `is_administrador` boolean NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `nome`, `email`, `senha`, `is_administrador`) VALUES
(1, 'Administrador', 'adm@gmail.com', 'adm1234', true),
(1, 'Usuario', 'user@gmail.com', 'user1234', false),

-- --------------------------------------------------------

--
-- Estrutura da tabela `historico_lixeira_sensor_capacidade`
--

CREATE TABLE `historico_lixeira_sensor_capacidade` (
  `id_historico_capacidade` int(11) NOT NULL,
  `id_lixeira` int(11) NOT NULL,
  `capacidade` float NOT NULL COMMENT '0 (vazia) até 1 (cheia)',
  `data` date NOT NULL DEFAULT current_timestamp(),
  `hora` time NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `historico_lixeira_sensor_capacidade`
--

INSERT INTO `historico_lixeira_sensor_capacidade` (`id_historico_capacidade`, `id_lixeira`, `capacidade`, `data`, `hora`) VALUES
(1, 12345, 0.8, '2020-04-26', '18:30:35'),
(3, 12345, 0.5, '2020-04-28', '20:24:00'),
(4, 121212, 0, '2020-05-10', '16:34:06');

-- --------------------------------------------------------

--
-- Estrutura da tabela `historico_lixeira_sensor_tampa`
--

CREATE TABLE `historico_lixeira_sensor_tampa` (
  `id_historico_tampa` int(11) NOT NULL,
  `id_lixeira` int(11) NOT NULL,
  `valor` tinyint(1) NOT NULL,
  `data` date NOT NULL DEFAULT current_timestamp(),
  `hora` time NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `historico_lixeira_sensor_tampa`
--

INSERT INTO `historico_lixeira_sensor_tampa` (`id_historico_tampa`, `id_lixeira`, `valor`, `data`, `hora`) VALUES
(1, 12345, 1, '2020-04-26', '17:14:05'),
(3, 12345, 1, '2020-04-28', '21:20:17'),
(4, 121212, 1, '2020-05-10', '16:36:07');

-- --------------------------------------------------------

--
-- Estrutura da tabela `lixeira`
--

CREATE TABLE `lixeira` (
  `id_lixeira` int(11) NOT NULL,
  `lat` float NOT NULL,
  `lng` float NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `ambiente` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `lixeira`
--

INSERT INTO `lixeira` (`id_lixeira`, `lat`, `lng`, `tipo`, `ambiente`) VALUES
(12345, -22.3821, -47.5461, 'Metal', 'Condomínio'),
(121212, -22.98, -23.9, 'Orgânico', 'Centro');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `produto`
--
ALTER TABLE `produto`
  ADD PRIMARY KEY (`id_produto`),
  ADD KEY `id_lixeira` (`id_lixeira`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Índices para tabela `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Índices para tabela `historico_lixeira_sensor_capacidade`
--
ALTER TABLE `historico_lixeira_sensor_capacidade`
  ADD PRIMARY KEY (`id_historico_capacidade`),
  ADD KEY `id_lixeira` (`id_lixeira`);

--
-- Índices para tabela `historico_lixeira_sensor_tampa`
--
ALTER TABLE `historico_lixeira_sensor_tampa`
  ADD PRIMARY KEY (`id_historico_tampa`),
  ADD KEY `id_lixeira` (`id_lixeira`);

--
-- Índices para tabela `lixeira`
--
ALTER TABLE `lixeira`
  ADD PRIMARY KEY (`id_lixeira`),
  ADD KEY `id_lixeira` (`id_lixeira`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `produto`
--
ALTER TABLE `produto`
  MODIFY `id_produto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `historico_lixeira_sensor_capacidade`
--
ALTER TABLE `historico_lixeira_sensor_capacidade`
  MODIFY `id_historico_capacidade` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de tabela `historico_lixeira_sensor_tampa`
--
ALTER TABLE `historico_lixeira_sensor_tampa`
  MODIFY `id_historico_tampa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
