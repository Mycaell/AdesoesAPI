package com.klok.treinamento.adesoes.api.presentation.cobranca;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.klok.treinamento.adesoes.api.domain.enums.Status;
import com.klok.treinamento.adesoes.api.domain.model.Pagamento;
import com.klok.treinamento.adesoes.api.presentation.adesao.AdesaoResponse;

public class CobrancaResponse {
	


	private Long codigo;
	private Integer parcela;

	
	private BigDecimal valor;
	private LocalDate data;
	private Status status;
	
	// add seu respectivo dto
	private List<Pagamento> pagamentos;
	private AdesaoResponse adesao;

	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getParcela() {
		return parcela;
	}

	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public AdesaoResponse getAdesao() {
		return adesao;
	}

	public void setAdesao(AdesaoResponse adesao) {
		this.adesao = adesao;
	}
	


	
	
	
	
	


}
