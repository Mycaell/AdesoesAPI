package com.klok.treinamento.adesoes.api.presentation.adesao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.klok.treinamento.adesoes.api.domain.enums.ModalidadeAdesao;
import com.klok.treinamento.adesoes.api.domain.enums.StatusAdesao;
import com.klok.treinamento.adesoes.api.domain.model.Atualizacao;
import com.klok.treinamento.adesoes.api.domain.model.Cancelamento;
import com.klok.treinamento.adesoes.api.domain.model.Cobranca;
import com.klok.treinamento.adesoes.api.presentation.contratante.ContratanteResponse;
import com.klok.treinamento.adesoes.api.presentation.produto.ProdutoResponse;
import com.klok.treinamento.adesoes.api.presentation.resposta.RespostaResponse;

public class AdesaoResponse {
	


	private Long codigo;
	private ProdutoResponse produto;
	private ContratanteResponse contratante;
	
	private BigDecimal valor;
	private Integer parcelas;
	private Integer diaCobranca;
	
	private ModalidadeAdesao modalidade;
	private StatusAdesao status;
	
	private LocalDate inicio;
	private LocalDate termino;
	
	// add seus respectivos dtos
	@JsonIgnoreProperties("adesao")
	private List<RespostaResponse> respostas;
	private List<Cobranca> cobrancas;
	private List<Atualizacao> atualizacoes;
	private List<Cancelamento> cancelamentos;


	






	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public ProdutoResponse getProduto() {
		return produto;
	}

	public void setProduto(ProdutoResponse produto) {
		this.produto = produto;
	}

	public ContratanteResponse getContratante() {
		return contratante;
	}

	public void setContratante(ContratanteResponse contratante) {
		this.contratante = contratante;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer parcelas) {
		this.parcelas = parcelas;
	}

	public Integer getDiaCobranca() {
		return diaCobranca;
	}

	public void setDiaCobranca(Integer diaCobranca) {
		this.diaCobranca = diaCobranca;
	}

	public ModalidadeAdesao getModalidade() {
		return modalidade;
	}

	public void setModalidade(ModalidadeAdesao modalidade) {
		this.modalidade = modalidade;
	}

	public StatusAdesao getStatus() {
		return status;
	}

	public void setStatus(StatusAdesao status) {
		this.status = status;
	}

	public LocalDate getInicio() {
		return inicio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public LocalDate getTermino() {
		return termino;
	}

	public void setTermino(LocalDate termino) {
		this.termino = termino;
	}

	

	public List<RespostaResponse> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<RespostaResponse> respostas) {
		this.respostas = respostas;
	}

	public List<Cobranca> getCobrancas() {
		return cobrancas;
	}

	public void setCobrancas(List<Cobranca> cobrancas) {
		this.cobrancas = cobrancas;
	}

	public List<Atualizacao> getAtualizacoes() {
		return atualizacoes;
	}
	
	public void setAtualizacoes(List<Atualizacao> atualizacoes) {
		this.atualizacoes = atualizacoes;
	}

	public List<Cancelamento> getCancelamentos() {
		return cancelamentos;
	}

	public void setCancelamentos(List<Cancelamento> cancelamentos) {
		this.cancelamentos = cancelamentos;
	}
	

	
	
	
	
	
	
	
	


}
