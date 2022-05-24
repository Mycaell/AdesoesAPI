CREATE TABLE adesao(
	codigo SERIAL PRIMARY KEY,	
	modalidade VARCHAR(50) NOT NULL,
	status VARCHAR(50) NOT NULL,
	valor NUMERIC check (valor > 0) NOT NULL,
	num_parcelas INTEGER NOT NULL,
	dia_cobranca INTEGER NOT NULL,
	data_inicio DATE NOT NULL,
	data_termino DATE NOT NULL,
	codigo_contratante INTEGER NOT NULL,	
	codigo_produto INTEGER NOT NULL,
	
	FOREIGN KEY (codigo_contratante) REFERENCES contratante (codigo),
	FOREIGN KEY (codigo_produto) REFERENCES produto (codigo)
);