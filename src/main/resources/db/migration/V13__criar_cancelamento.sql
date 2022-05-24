CREATE TABLE cancelamento(
	codigo SERIAL PRIMARY KEY,	
	motivo TEXT NULL,
	data TIMESTAMP NOT NULL,
	codigo_adesao INTEGER NOT NULL,	

	FOREIGN KEY (codigo_adesao) REFERENCES adesao (codigo)
);
