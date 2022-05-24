package com.klok.treinamento.adesoes.api.application.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.klok.treinamento.adesoes.api.domain.exceptions.adesao.AdesaoNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.exceptions.campo.CampoObrigatorioException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaNaoEncontradaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.cobertura.CoberturaObrigatoriaException;
import com.klok.treinamento.adesoes.api.domain.exceptions.produto.ProdutoNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.exceptions.resposta.RespostaObrigatoriaException;

@ControllerAdvice
public class AdesoesResponseExceptionHandler extends ResponseEntityExceptionHandler {

	
	@Autowired
	private MessageSource messageSource;
	
	
	private String montarMensagemUsuario(String sourceMessage) {
		return messageSource.getMessage(sourceMessage, null, LocaleContextHolder.getLocale());
	}
	
	private List<MensagemDeErro> montarResponseBody(HttpStatus status, String mensagemUsuario, String mensagemDesenvolvedor) {
		return Arrays.asList(new MensagemDeErro(mensagemUsuario, mensagemDesenvolvedor, status.value(), System.currentTimeMillis()));
	}
	

	private List<MensagemDeErro> criarListaDeErros(BindingResult bindingResult, HttpStatus status) {
		
		List<MensagemDeErro> erros = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString();
			
			erros.add(new MensagemDeErro(mensagemUsuario, mensagemDesenvolvedor, status.value(), System.currentTimeMillis()));
		}
		
		return erros;
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("mensagem.invalida");
		String mensagemDesenvolvedor = ex.getCause().toString();
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(ex, responseBody, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		status = HttpStatus.BAD_REQUEST;
		List<MensagemDeErro> responseBody = criarListaDeErros(ex.getBindingResult(), status);
		return handleExceptionInternal(ex, responseBody, headers, status, request);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessExeption(EmptyResultDataAccessException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		String mensagemUsuario = montarMensagemUsuario("recurso.nao-encontrado");
		String mensagemDesenvolvedor = exception.toString();
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("recurso.operacao-nao-permitida");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	
	
	
	
	
//	# Adesão
	
	@ExceptionHandler({ AdesaoNaoEncontradaException.class })
	public ResponseEntity<Object> handleAdesaoNaoEncontradaException(AdesaoNaoEncontradaException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("adesao.nao-encontrada");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	
	
	
	
//	# Produto
	
	@ExceptionHandler({ ProdutoNaoEncontradoException.class })
	public ResponseEntity<Object> handleProdutoNaoEncontradoException(ProdutoNaoEncontradoException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("produto.nao-encontrado");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	
//	# Campo
	
	@ExceptionHandler({ CampoNaoEncontradoException.class })
	public ResponseEntity<Object> handleCampoNaoEncontradoException(CampoNaoEncontradoException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("campo.nao-encontrado");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ CampoExistenteException.class })
	public ResponseEntity<Object> handleCampoExistenteException(CampoExistenteException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("campo.existente");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ CampoObrigatorioException.class })
	public ResponseEntity<Object> handleCampoObrigatorioException(CampoObrigatorioException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("campo.obrigatorio");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
//	# Cobertura
	
	@ExceptionHandler({ CoberturaNaoEncontradaException.class })
	public ResponseEntity<Object> handleCoberturaNaoEncontradaException(CoberturaNaoEncontradaException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("cobertura.nao-encontrada");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ CoberturaExistenteException.class })
	public ResponseEntity<Object> handleCoberturaExistenteException(CoberturaExistenteException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("cobertura.existente");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ CoberturaObrigatoriaException.class })
	public ResponseEntity<Object> handleCoberturaObrigatoriaException(CoberturaObrigatoriaException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("cobertura.obrigatoria");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
//	# Resposta
	
	@ExceptionHandler({ RespostaObrigatoriaException.class })
	public ResponseEntity<Object> handleRespostaObrigatoriaException(RespostaObrigatoriaException exception,
			WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		String mensagemUsuario = montarMensagemUsuario("resposta.obrigatoria");
		String mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(exception);
		List<MensagemDeErro> responseBody = montarResponseBody(status, mensagemUsuario, mensagemDesenvolvedor);

		return handleExceptionInternal(exception, responseBody, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	
	
}

