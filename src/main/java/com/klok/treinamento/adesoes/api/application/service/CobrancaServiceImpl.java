package com.klok.treinamento.adesoes.api.application.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.klok.treinamento.adesoes.api.domain.enums.ModalidadeAdesao;
import com.klok.treinamento.adesoes.api.domain.enums.Status;
import com.klok.treinamento.adesoes.api.domain.enums.StatusAdesao;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobranca.CobrancaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.model.Adesao;
import com.klok.treinamento.adesoes.api.domain.model.Cobranca;
import com.klok.treinamento.adesoes.api.domain.service.CobrancaService;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.AdesaoRepository;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.CobrancaRepository;
import com.klok.treinamento.adesoes.api.infrastructure.rabbitmq.RabbitmqConstantes;
import com.klok.treinamento.adesoes.api.infrastructure.rabbitmq.RabbitmqService;


@Service
public class CobrancaServiceImpl implements CobrancaService{
	
	@Autowired
	private CobrancaRepository cobrancaRepository;
	
	@Autowired
	private AdesaoRepository adesaoRepository;
	
	@Autowired
	private RabbitmqService rabbitmqService;
	
	public Cobranca gerarCobranca(Adesao adesao) {
		
		Cobranca cobranca = new Cobranca();
		
		cobranca.setData(LocalDate.now());
		
		cobranca.setAdesao(adesao);
		
		if(adesao.getModalidade() == ModalidadeAdesao.RECORRENTE) {
			
			int parcela = adesao.getTermino().getMonthValue() - LocalDate.now().getMonthValue();
			
			cobranca.setParcela(parcela);
			
			BigDecimal valorParcela = adesao.getValor().divideToIntegralValue(new BigDecimal(adesao.getParcelas()));
			cobranca.setValor(valorParcela);
		}else {
			cobranca.setValor(adesao.getValor());
		}		
		
		cobranca.setStatus(Status.SUCESSO);
		
		rabbitmqService.enviaMensagem(RabbitmqConstantes.FILA_COBRANCA, cobranca);
		  
		return cobranca;
	}

	
	@Override
	public void cadastrar(Cobranca cobranca) {
		cobrancaRepository.save(cobranca);
	}
	
	@Override
	public void atualizar(Cobranca cobrancaBuscada, Cobranca cobranca) {
		BeanUtils.copyProperties(cobranca, cobrancaBuscada, "codigo");
		cobrancaRepository.save(cobrancaBuscada);
	}
	
	
	@Override
	@Scheduled(cron = "0/10 * * ? * *") // alterar esse valor para um q faça mais sentido
	public void gerarCobrancasRecorrentes() {
		
		System.out.println("CRON executado!");
		
		List<Adesao> adesoes = adesaoRepository.findAll();

		for (Adesao adesao : adesoes) {
			
			LocalDate dataAtual = LocalDate.now();
			
			if(adesao.getStatus() == StatusAdesao.ATIVA && dataAtual.isBefore(adesao.getTermino())) {
				
				if(adesao.getDiaCobranca() == dataAtual.getDayOfMonth()) { // TODO: aprimorar, verificar se já existe uma cobranca pra essa adesão na data atual
					gerarCobranca(adesao);
				}
				
			}
		}
		

	}
	
	
	
	@Override
	public Cobranca buscar(Long codigo) throws CobrancaNaoEncontradaException {
		Optional<Cobranca> cobrancaBuscada = cobrancaRepository.findById(codigo);
		
		if(cobrancaBuscada.isEmpty()) {
			throw new CobrancaNaoEncontradaException("Não foi encontrada uma cobranca com o id: " + codigo);
		}
		
		return cobrancaBuscada.get();
	}

	@Override
	public List<Cobranca> listar() {
		return cobrancaRepository.findAll();
	}

	
	
	


}
