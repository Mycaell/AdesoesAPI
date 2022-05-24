package com.klok.treinamento.adesoes.api.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Campo;
import com.klok.treinamento.adesoes.api.domain.service.CampoService;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.CampoRepository;


@Service
public class CampoServiceImpl implements CampoService{

	@Autowired
	private CampoRepository campoRepository;
	
	@Override
	public Campo cadastrar(Campo campo) throws CampoExistenteException{
		
		verificarExistenciaDeCampo(campo);
		
		return campoRepository.save(campo);
	}
	

	@Override
	public Campo atualizar(Long codigo, Campo campo) throws CampoNaoEncontradoException, CampoExistenteException {
		
		Optional<Campo> campoOptional = campoRepository.findById(codigo);

		if(campoOptional.isEmpty()) {
			throw new CampoNaoEncontradoException("Não foi encontrado um campo com o id: " + codigo);
		}
		
		Campo campoDoBanco = campoOptional.get();
		
		verificarExistenciaDeCampo(campo);
		
		
		BeanUtils.copyProperties(campo, campoDoBanco, "codigo");
				
		return campoRepository.save(campoDoBanco);
	}

	
	@Override
	public Campo buscar(Long codigo) throws CampoNaoEncontradoException {
		Optional<Campo> campoBuscado = campoRepository.findById(codigo);
	
		if(campoBuscado.isEmpty()) {
			throw new CampoNaoEncontradoException("Não foi encontrado um campo com o id: " + codigo);
		}
		
		return campoBuscado.get();
	}
	

	@Override
	public List<Campo> listar() {
		return campoRepository.findAll();
	}


	@Override
	public void excluir(Long codigo) throws CampoNaoEncontradoException {

		Optional<Campo> campoOptional = campoRepository.findById(codigo);
		
		if(campoOptional.isEmpty()) {
			throw new CampoNaoEncontradoException("Não foi encontrado um campo com o id: " + codigo);
		}
		
		campoRepository.deleteById(codigo);
	}

	
	
	private boolean verificarExistenciaDeCampo(Campo campo) throws CampoExistenteException {
		List<Campo> campos = campoRepository.findAll();
		
		for (Campo c : campos) {
			if(campo.getNome().equals(c.getNome()))
				throw new CampoExistenteException("O campo " + campo.getNome() + " já existe.");
		}
		
		return false;
	}
	
	
	


}
