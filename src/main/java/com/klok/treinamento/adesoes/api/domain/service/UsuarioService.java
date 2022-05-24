package com.klok.treinamento.adesoes.api.domain.service;

import java.util.List;

import com.klok.treinamento.adesoes.api.domain.exceptions.usuario.UsuarioExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.usuario.UsuarioNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Usuario;

public interface UsuarioService {	
	
	public Usuario cadastrar(Usuario usuario) throws UsuarioExistenteException; 
	
	public Usuario atualizar(Long codigo, Usuario usuario) throws UsuarioNaoEncontradoException, UsuarioExistenteException ; 
	
	public Usuario buscar(Long codigo) throws UsuarioNaoEncontradoException; 
	
	public List<Usuario> listar();
	
	public void excluir(Long codigo) throws UsuarioNaoEncontradoException;
	
}
