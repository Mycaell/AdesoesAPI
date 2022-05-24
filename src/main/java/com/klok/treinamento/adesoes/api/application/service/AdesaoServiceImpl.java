package com.klok.treinamento.adesoes.api.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klok.treinamento.adesoes.api.domain.enums.StatusAdesao;
import com.klok.treinamento.adesoes.api.domain.exceptions.adesao.AdesaoNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.exceptions.resposta.RespostaObrigatoriaException;
import com.klok.treinamento.adesoes.api.domain.model.Adesao;
import com.klok.treinamento.adesoes.api.domain.model.Resposta;
import com.klok.treinamento.adesoes.api.domain.service.AdesaoService;
import com.klok.treinamento.adesoes.api.domain.service.CampoService;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.AdesaoRepository;


@Service
public class AdesaoServiceImpl implements AdesaoService{

	@Autowired
	private AdesaoRepository adesaoRepository;
	
	@Autowired
	private CampoService campoService;
	
	

	@Override
	public Adesao cadastrar(Adesao adesao) throws RespostaObrigatoriaException {

		System.out.println("ADESA \n"+ adesao);
		
		adesao.setStatus(StatusAdesao.ATIVA);
		adesao.setInicio(LocalDate.now());
		
		
		LocalDate dataTermino = LocalDate.now();
		dataTermino = dataTermino.plusMonths(adesao.getParcelas());
		adesao.setTermino(dataTermino);
		
		
		setarCamposEmRespostas(adesao);
		
		
		// TODO: verificas se os campos obrigatorios foram preenchidos
		
		Adesao adesaoSalva = adesaoRepository.save(adesao);
					
		return adesaoSalva;
	}

	@Override
	public Adesao atualizar(Long codigo, Adesao adesao) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adesao buscar(Long codigo) throws AdesaoNaoEncontradaException {
		Optional<Adesao> adesaoBuscada = adesaoRepository.findById(codigo);
		
		if(adesaoBuscada.isEmpty()) {
			throw new AdesaoNaoEncontradaException("Não foi encontrada uma adesão com o id: " + codigo);
		}
		
		return adesaoBuscada.get();
	}

	@Override
	public List<Adesao> listar() {
		return adesaoRepository.findAll();
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		
	}


	private void setarCamposEmRespostas(Adesao adesao) throws RespostaObrigatoriaException{
		
		if(adesao.getRespostas() == null || adesao.getRespostas().size() == 0) {
			throw new RespostaObrigatoriaException("Para cadastar uma adesão é necessário informar uma ou mais respostas.");
		}
		
		
		
		
		for (Resposta resposta : adesao.getRespostas()) {
			try {
				resposta.setCampo(campoService.buscar(resposta.getCampo().getCodigo()));
			} catch (CampoNaoEncontradoException e) {
				e.printStackTrace();
			}
		}
	}
	


}
