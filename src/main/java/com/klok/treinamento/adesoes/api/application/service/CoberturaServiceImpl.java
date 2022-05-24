package com.klok.treinamento.adesoes.api.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.model.Cobertura;
import com.klok.treinamento.adesoes.api.domain.service.CoberturaService;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.CoberturaRepository;


@Service
public class CoberturaServiceImpl implements CoberturaService{

	@Autowired
	private CoberturaRepository coberturaRepository;

	@Override
	public Cobertura cadastrar(Cobertura cobertura) throws CoberturaExistenteException{
		
		verificarExistenciaDeCobertura(cobertura);
		
		return coberturaRepository.save(cobertura);
	}
	

	@Override
	public Cobertura atualizar(Long codigo, Cobertura cobertura) throws CoberturaNaoEncontradaException, CoberturaExistenteException {
		
		Optional<Cobertura> coberturaOptional = coberturaRepository.findById(codigo);

		if(coberturaOptional.isEmpty()) {
			throw new CoberturaNaoEncontradaException("Não foi encontrada uma cobertura com o id: " + codigo);
		}
		
		Cobertura coberturaDoBanco = coberturaOptional.get();

		verificarExistenciaDeCobertura(cobertura);
		
		BeanUtils.copyProperties(cobertura, coberturaDoBanco, "codigo");
				
		return coberturaRepository.save(coberturaDoBanco);
	}
	

	@Override
	public Cobertura buscar(Long codigo) throws CoberturaNaoEncontradaException {
		Optional<Cobertura> coberturaBuscada = coberturaRepository.findById(codigo);
	
		if(coberturaBuscada.isEmpty()) {
			throw new CoberturaNaoEncontradaException("Não foi encontrada uma cobertura com o id: " + codigo);
		}
		
		return coberturaBuscada.get();
	}

	
	@Override
	public List<Cobertura> listar() {
		return coberturaRepository.findAll();
	}

	
	@Override
	public void excluir(Long codigo) throws CoberturaNaoEncontradaException {

		Optional<Cobertura> coberturaOptional = coberturaRepository.findById(codigo);
		
		if(coberturaOptional.isEmpty()) {
			throw new CoberturaNaoEncontradaException("Não foi encontrada uma cobertura com o id: " + codigo);
		}
		
		coberturaRepository.deleteById(codigo);
	}

	
	private boolean verificarExistenciaDeCobertura(Cobertura cobertura) throws CoberturaExistenteException {
		
		List<Cobertura> coberturas = coberturaRepository.findAll();
		
		for (Cobertura c : coberturas) {
			if(cobertura.getNome().equals(c.getNome()))
				throw new CoberturaExistenteException("A cobertura " + cobertura.getNome() + " já existe.");
		}
		
		return false;
	}
	
	


}
