package com.klok.treinamento.adesoes.api.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.klok.treinamento.adesoes.api.domain.enums.ModalidadeAdesao;
import com.klok.treinamento.adesoes.api.domain.enums.StatusAdesao;

@Entity
@Table(name = "adesao")
public class Adesao  implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;
	
	@NotNull
	@Column(name = "valor")
	private BigDecimal valor;
	
	@NotNull
	@Column(name = "num_parcelas")
	private Integer parcelas;
	
	@NotNull
	@Column(name = "dia_cobranca")
	private Integer diaCobranca;
	
	@NotNull
	@Column(name = "modalidade")
	@Enumerated(EnumType.STRING)
	private ModalidadeAdesao modalidade;
	
	@NotNull
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private StatusAdesao status;
	
	@NotNull
	@Column(name = "data_inicio")
	private LocalDate inicio;
	
	@NotNull
	@Column(name = "data_termino")
	private LocalDate termino;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_contratante")
	private Contratante contratante;
	
	@NotNull
	@JsonIgnoreProperties({"adesoes", "coberturas", "campos"})
	@ManyToOne
	@JoinColumn(name = "codigo_produto")
	private Produto produto;
	
	@JsonIgnoreProperties({"adesao", "pagamentos"})
	@OneToMany(mappedBy = "adesao")
	private List<Cobranca> cobrancas;
	
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_adesao")
	private List<Resposta> respostas;
	
	@OneToMany(mappedBy = "adesao")
	private List<Atualizacao> atualizacoes;
	
	@OneToMany(mappedBy = "adesao")
	private List<Cancelamento> cancelamentos;

	
	
	
	
	
	
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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

	public Contratante getContratante() {
		return contratante;
	}

	public void setContratante(Contratante contratante) {
		this.contratante = contratante;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Cobranca> getCobrancas() {
		return cobrancas;
	}

	public void setCobrancas(List<Cobranca> cobrancas) {
		this.cobrancas = cobrancas;
	}

	public List<Resposta> getRespostas() {
		return respostas;
	}

	public void setRespostas(List<Resposta> respostas) {
		this.respostas = respostas;
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

	@Override
	public String toString() {
		return "Adesao [codigo=" + codigo + ", valor=" + valor + ", parcelas=" + parcelas + ", diaCobranca="
				+ diaCobranca + ", modalidade=" + modalidade + ", status=" + status + ", inicio=" + inicio
				+ ", termino=" + termino + ", contratante=" + contratante + ", produto=" + produto + ", cobrancas="
				+ cobrancas + ", respostas=" + respostas + ", atualizacoes=" + atualizacoes + ", cancelamentos="
				+ cancelamentos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adesao other = (Adesao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	
	
	
	
	
}
