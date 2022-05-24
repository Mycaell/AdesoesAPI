CREATE TABLE cobranca(
	codigo SERIAL PRIMARY KEY,
	parcela INTEGER NOT NULL,
	valor NUMERIC check (valor > 0) NOT NULL,
	data DATE NOT NULL,	
	status VARCHAR(50) NOT NULL,
	codigo_adesao INTEGER NOT NULL,
	
	FOREIGN KEY (codigo_adesao) REFERENCES adesao (codigo)
);