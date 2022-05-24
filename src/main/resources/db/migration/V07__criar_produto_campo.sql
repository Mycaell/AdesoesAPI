CREATE TABLE produto_campo(
	codigo_produto INTEGER NOT NULL,
	codigo_campo INTEGER NOT NULL,

	FOREIGN KEY (codigo_produto) REFERENCES produto (codigo),
	FOREIGN KEY (codigo_campo) REFERENCES campo (codigo)
);