package com.klok.treinamento.adesoes.api.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klok.treinamento.adesoes.api.domain.exceptions.papel.PapelExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.papel.PapelNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Papel;
import com.klok.treinamento.adesoes.api.domain.service.PapelService;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.PapelRepository;


@Service
public class PapelServiceImpl implements PapelService{

	@Autowired
	private PapelRepository papelRepository;
	
	@Override
	public Papel cadastrar(Papel papel) throws PapelExistenteException{
		
		verificarExistenciaDePapel(papel);
		
		return papelRepository.save(papel);
	}
	

	@Override
	public Papel atualizar(Long codigo, Papel papel) throws PapelNaoEncontradoException, PapelExistenteException {
		
		Optional<Papel> papelOptional = papelRepository.findById(codigo);

		if(papelOptional.isEmpty()) {
			throw new PapelNaoEncontradoException("Não foi encontrado um papel com o id: " + codigo);
		}
		
		Papel papelDoBanco = papelOptional.get();
		
		verificarExistenciaDePapel(papel);
		
		
		BeanUtils.copyProperties(papel, papelDoBanco, "codigo");
				
		return papelRepository.save(papelDoBanco);
	}

	
	@Override
	public Papel buscar(Long codigo) throws PapelNaoEncontradoException {
		Optional<Papel> papelBuscado = papelRepository.findById(codigo);
	
		if(papelBuscado.isEmpty()) {
			throw new PapelNaoEncontradoException("Não foi encontrado um papel com o id: " + codigo);
		}
		
		return papelBuscado.get();
	}
	

	@Override
	public List<Papel> listar() {
		return papelRepository.findAll();
	}


	@Override
	public void excluir(Long codigo) throws PapelNaoEncontradoException {

		Optional<Papel> papelOptional = papelRepository.findById(codigo);
		
		if(papelOptional.isEmpty()) {
			throw new PapelNaoEncontradoException("Não foi encontrado um papel com o id: " + codigo);
		}
		
		papelRepository.deleteById(codigo);
	}

	
	
	private boolean verificarExistenciaDePapel(Papel papel) throws PapelExistenteException {
		List<Papel> papeis = papelRepository.findAll();
		
		for (Papel p : papeis) {
			if(papel.getNome() == p.getNome())
				throw new PapelExistenteException("O papel " + papel.getNome() + " já existe.");
		}
		
		return false;
	}
	
	
	


}
