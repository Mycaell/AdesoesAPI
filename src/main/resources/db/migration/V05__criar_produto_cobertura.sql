CREATE TABLE produto_cobertura (
	codigo_produto INTEGER NOT NULL,
	codigo_cobertura INTEGER NOT NULL,

	FOREIGN KEY (codigo_produto) REFERENCES produto (codigo),
	FOREIGN KEY (codigo_cobertura) REFERENCES cobertura (codigo)
);