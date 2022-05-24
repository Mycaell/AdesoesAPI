package com.klok.treinamento.adesoes.api.application.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.klok.treinamento.adesoes.api.application.event.RecursoCriadoEvent;
import com.klok.treinamento.adesoes.api.application.service.UsuarioServiceImpl;
import com.klok.treinamento.adesoes.api.domain.exceptions.usuario.UsuarioExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.usuario.UsuarioNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Usuario;
import com.klok.treinamento.adesoes.api.infrastructure.converter.custom.ConverterServiceImpl;
import com.klok.treinamento.adesoes.api.presentation.usuario.UsuarioRequest;
import com.klok.treinamento.adesoes.api.presentation.usuario.UsuarioResponse;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private ConverterServiceImpl converter;
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UsuarioResponse> cadastrar(@Valid @RequestBody UsuarioRequest usuarioRequest, HttpServletResponse response) throws UsuarioExistenteException {
		
		Usuario usuario = converter.convert(usuarioRequest, Usuario.class);
		
		usuario = usuarioServiceImpl.cadastrar(usuario);
		
		UsuarioResponse usuarioResponse = converter.convert(usuario, UsuarioResponse.class);
		
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuario.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioResponse);
	}
	
	
	@PutMapping("/{codigo}") 
	public ResponseEntity<UsuarioResponse> atualizar(@PathVariable Long codigo, @Valid @RequestBody UsuarioRequest usuarioRequest) throws UsuarioNaoEncontradoException, UsuarioExistenteException {
		
		Usuario usuario = converter.convert(usuarioRequest, Usuario.class);
		
		usuario = usuarioServiceImpl.atualizar(codigo, usuario);
		
		UsuarioResponse usuarioResponse = converter.convert(usuario, UsuarioResponse.class);
		
		
		return ResponseEntity.ok(usuarioResponse);
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<UsuarioResponse> buscar(@PathVariable Long codigo) throws UsuarioNaoEncontradoException {
		Usuario usuarioBuscado = usuarioServiceImpl.buscar(codigo);

		System.out.println(usuarioBuscado);
		
		UsuarioResponse usuarioResponse = converter.convert(usuarioBuscado, UsuarioResponse.class);
		
		return ResponseEntity.ok(usuarioResponse);
	}
	
	
	@GetMapping
	public List<UsuarioResponse> listar(){
		List<Usuario> usuarios = usuarioServiceImpl.listar();
		
		List<UsuarioResponse> listaUsuariosResponse = converter.mapList(usuarios, UsuarioResponse.class);
		
		return listaUsuariosResponse;
	}
	
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> delete(@PathVariable Long codigo) throws UsuarioNaoEncontradoException {
		usuarioServiceImpl.excluir(codigo);
		return ResponseEntity.noContent().build();
	}
}
