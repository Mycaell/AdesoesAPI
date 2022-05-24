package com.klok.treinamento.adesoes.api.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klok.treinamento.adesoes.api.domain.model.Pagamento;
import com.klok.treinamento.adesoes.api.domain.service.PagamentoService;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.PagamentoRepository;


@Service
public class PagamentoServiceImpl implements PagamentoService{

	@Autowired
	private PagamentoRepository pagamentoRepository;


	@Override
	public Pagamento cadastrar(Pagamento pagamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagamento atualizar(Long codigo, Pagamento pagamento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pagamento buscar(Long codigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pagamento> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		
	}
	

	


}
