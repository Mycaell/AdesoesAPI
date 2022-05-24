package com.klok.treinamento.adesoes.api.application.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.klok.treinamento.adesoes.api.domain.exceptions.usuario.UsuarioExistenteException;
import com.klok.treinamento.adesoes.api.domain.exceptions.usuario.UsuarioNaoEncontradoException;
import com.klok.treinamento.adesoes.api.domain.model.Usuario;
import com.klok.treinamento.adesoes.api.domain.service.UsuarioService;
import com.klok.treinamento.adesoes.api.infrastructure.persistence.UsuarioRepository;


@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario cadastrar(Usuario usuario) throws UsuarioExistenteException{
		
		verificarExistenciaDeUsuario(usuario);
		
		usuario.setSenha(senhaToBcrypt(usuario.getSenha()));
		
		return usuarioRepository.save(usuario);
	}
	

	@Override
	public Usuario atualizar(Long codigo, Usuario usuario) throws UsuarioNaoEncontradoException, UsuarioExistenteException {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(codigo);

		if(usuarioOptional.isEmpty()) {
			throw new UsuarioNaoEncontradoException("Não foi encontrado um usuário com o id: " + codigo);
		}
		
		Usuario usuarioDoBanco = usuarioOptional.get();
		
		verificarExistenciaDeUsuario(usuario);
		
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(!passwordEncoder.matches(usuario.getSenha(), usuarioDoBanco.getSenha())) {
			usuario.setSenha(senhaToBcrypt(usuario.getSenha()));
		}
		
		
		
		
		BeanUtils.copyProperties(usuario, usuarioDoBanco, "codigo");
				
		return usuarioRepository.save(usuarioDoBanco);
	}

	
	@Override
	public Usuario buscar(Long codigo) throws UsuarioNaoEncontradoException {
		Optional<Usuario> usuarioBuscado = usuarioRepository.findById(codigo);
	
		if(usuarioBuscado.isEmpty()) {
			throw new UsuarioNaoEncontradoException("Não foi encontrado um usuário com o id: " + codigo);
		}
		
		return usuarioBuscado.get();
	}
	

	@Override
	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}


	@Override
	public void excluir(Long codigo) throws UsuarioNaoEncontradoException {

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(codigo);
		
		if(usuarioOptional.isEmpty()) {
			throw new UsuarioNaoEncontradoException("Não foi encontrado um usuário com o id: " + codigo);
		}
		
		usuarioRepository.deleteById(codigo);
	}

	
	
	private boolean verificarExistenciaDeUsuario(Usuario usuario) throws UsuarioExistenteException {
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		for (Usuario u : usuarios) {
			if(usuario.getLogin().equals(u.getLogin()))
				throw new UsuarioExistenteException("Já existe um usuário cadastrado com esse login "+usuario.getLogin());
		}
		
		return false;
	}
	
	public String senhaToBcrypt(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}
	


}
