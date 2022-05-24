package com.klok.treinamento.adesoes.api.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klok.treinamento.adesoes.api.domain.exceptions.contratante.ContratanteExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.contratante.ContratanteNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Contratante;
import com.klok.treinamento.adesoes.api.domain.service.ContratanteService;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.ContratanteRepository;


@Service
public class ContratanteServiceImpl implements ContratanteService{

	@Autowired
	private ContratanteRepository contratanteRepository;

	@Override
	public Contratante cadastrar(Contratante contratante) throws ContratanteExistenteException {
		
		verificarExistenciaDeContratante(contratante);
		
		return contratanteRepository.save(contratante);
	}

	@Override
	public Contratante atualizar(Long codigo, Contratante contratante) throws ContratanteNaoEncontradoException, ContratanteExistenteException {
		Optional<Contratante> contratanteOptional = contratanteRepository.findById(codigo);

		if(contratanteOptional.isEmpty()) {
			throw new ContratanteNaoEncontradoException("Não foi encontrado um contratante com o id: " + codigo);
		}
		
		Contratante contratanteDoBanco = contratanteOptional.get();

		verificarExistenciaDeContratante(contratante);
		
		BeanUtils.copyProperties(contratante, contratanteDoBanco, "codigo");
				
		return contratanteRepository.save(contratanteDoBanco);
	}

	@Override
	public Contratante buscar(Long codigo) throws ContratanteNaoEncontradoException {
		Optional<Contratante> contratanteBuscado = contratanteRepository.findById(codigo);
		
		if(contratanteBuscado.isEmpty()) {
			throw new ContratanteNaoEncontradoException("Não foi encontrado um contratante com o id: " + codigo);
		}
		
		return contratanteBuscado.get();
	}

	@Override
	public List<Contratante> listar() {
		return contratanteRepository.findAll();
	}

	@Override
	public void excluir(Long codigo) throws ContratanteNaoEncontradoException {

		Optional<Contratante> contratanteOptional = contratanteRepository.findById(codigo);
		
		if(contratanteOptional.isEmpty()) {
			throw new ContratanteNaoEncontradoException("Não foi encontrado um contratante com o id: " + codigo);
		}
		
		contratanteRepository.deleteById(codigo);
	}


	
	private boolean verificarExistenciaDeContratante(Contratante contratante) throws ContratanteExistenteException {
		
		List<Contratante> contratantes = contratanteRepository.findAll();
		
		for (Contratante c : contratantes) {
			if(contratante.getCpf().equals(c.getCpf()))
				throw new ContratanteExistenteException("Já existe um contratante com esse CPF: " + contratante.getCpf());
		}
		
		return false;
	}
	
	


}
