CREATE TABLE pagamento(
	codigo SERIAL PRIMARY KEY,
	data TIMESTAMP NOT NULL,
	status VARCHAR(50) NOT NULL,
	codigo_cobranca INTEGER NOT NULL,

	FOREIGN KEY (codigo_cobranca) REFERENCES cobranca (codigo)
	
);