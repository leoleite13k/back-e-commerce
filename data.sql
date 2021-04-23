CREATE TABLE `usuario` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`email` varchar(150) NOT NULL,
	`nome` varchar(200) NOT NULL,
	`senha` varchar(255) NOT NULL,
	`administrador` BINARY NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `produto` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_usuario` INT,
	`nome` VARCHAR(200) NOT NULL,
	`preco` FLOAT NOT NULL,
	`foto` VARCHAR(255),
	`quantidade` INT NOT NULL,
	`descricao` VARCHAR(255),
	`promocao` BOOLEAN NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `compra` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_usuario` INT NOT NULL,
	`frete` FLOAT,
	PRIMARY KEY (`id`)
);

CREATE TABLE `compra_produto` (
	`id` INT NOT NULL,
	`id_produto` INT NOT NULL
);

ALTER TABLE `produto` ADD CONSTRAINT `produto_fk0` FOREIGN KEY (`id_usuario`) REFERENCES `usuario`(`id`);

ALTER TABLE `compra` ADD CONSTRAINT `compra_fk0` FOREIGN KEY (`id_usuario`) REFERENCES `usuario`(`id`);

ALTER TABLE `compra_produto` ADD CONSTRAINT `compra_produto_fk0` FOREIGN KEY (`id`) REFERENCES `compra`(`id`);

ALTER TABLE `compra_produto` ADD CONSTRAINT `compra_produto_fk1` FOREIGN KEY (`id_produto`) REFERENCES `produto`(`id`);

