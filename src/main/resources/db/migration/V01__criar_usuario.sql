CREATE TABLE usuario(
	codigo SERIAL PRIMARY KEY,	
	nome VARCHAR(50) NOT NULL,
	login VARCHAR(50) NOT NULL,
	senha VARCHAR(250) NOT NULL,
	codigo_papel INTEGER NOT NULL,
	UNIQUE(login),

	FOREIGN KEY (codigo_papel) REFERENCES papel (codigo)
);