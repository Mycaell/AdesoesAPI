package com.klok.treinamento.adesoes.api.domain.service;

import java.util.List;

import com.klok.treinamento.adesoes.api.domain.model.Pagamento;

public interface PagamentoService {
	
	
	public Pagamento cadastrar(Pagamento pagamento); 

	public Pagamento atualizar(Long codigo, Pagamento pagamento); 
	
	public Pagamento buscar(Long codigo);
	
	public List<Pagamento> listar(); 
	
	public void excluir(Long codigo);

	
}
