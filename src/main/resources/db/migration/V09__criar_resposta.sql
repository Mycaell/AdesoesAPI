CREATE TABLE resposta(
	codigo SERIAL PRIMARY KEY,
	conteudo VARCHAR (200) NOT NULL,
	codigo_campo INTEGER NOT NULL,
	codigo_adesao INTEGER,
	
	FOREIGN KEY (codigo_campo) REFERENCES campo (codigo),
	FOREIGN KEY (codigo_adesao) REFERENCES adesao (codigo)
);