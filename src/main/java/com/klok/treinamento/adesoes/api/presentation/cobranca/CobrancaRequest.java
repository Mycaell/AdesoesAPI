package com.klok.treinamento.adesoes.api.presentation.cobranca;

import java.math.BigDecimal;
import java.util.List;

import com.klok.treinamento.adesoes.api.domain.enums.ModalidadeAdesao;
import com.klok.treinamento.adesoes.api.domain.enums.StatusAdesao;
import com.klok.treinamento.adesoes.api.domain.model.Resposta;
import com.klok.treinamento.adesoes.api.presentation.contratante.ContratanteRequest;
import com.klok.treinamento.adesoes.api.presentation.produto.ProdutoRequest;

public class CobrancaRequest {
	

	private ProdutoRequest produto;
	private ContratanteRequest contratante;
	
	private BigDecimal valor;
	private Integer parcelas;
	private Integer diaCobranca;
	
	private ModalidadeAdesao modalidade;
	private StatusAdesao status;
	
	
	
	// add seu respectivo dto
	private List<Resposta> respostas;

	
	
	
	
	


	public ProdutoRequest getProduto() {
		return produto;
	}

	public void setProduto(ProdutoRequest produto) {
		this.produto = produto;
	}

	public ContratanteRequest getContratante() {
		return contratante;
	}

	public void setContratante(ContratanteRequest contratante) {
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

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
	}
	
	
	
	
	

	
	
	
	

	
	
}
