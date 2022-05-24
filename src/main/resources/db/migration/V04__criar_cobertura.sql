CREATE TABLE cobertura(
	codigo SERIAL PRIMARY KEY,	
	nome VARCHAR(50) NOT NULL,
	descricao TEXT NULL,
	valor NUMERIC check (valor > 0) NOT NULL,
	UNIQUE(nome)
);