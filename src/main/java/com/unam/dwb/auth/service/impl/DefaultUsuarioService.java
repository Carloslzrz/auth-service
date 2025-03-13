package com.unam.dwb.auth.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unam.dwb.auth.domain.Usuario;
import com.unam.dwb.auth.model.request.UsuarioRequest;
import com.unam.dwb.auth.model.response.UsuarioResponse;
import com.unam.dwb.auth.repo.UsuarioJdbcRepository;
import com.unam.dwb.auth.repo.UsuarioJpaRepository;
import com.unam.dwb.auth.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultUsuarioService implements UsuarioService {
	
	@Autowired
	private UsuarioJpaRepository usuarioJpaRepository;
	
	@Autowired
	private UsuarioJdbcRepository usuarioJdbcRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UsuarioResponse registraUsuario(@Valid UsuarioRequest request) {
		log.info("Se intenta registrar nuevo usuario");
		
		Boolean exitoPrecondiciones = verificaPrecondiciones(request);
		
		// Devolver respuesta de usuario ya existente
		if(!exitoPrecondiciones)
			return new UsuarioResponse(null);
		
		Usuario usuarioNuevo = new Usuario();
		usuarioNuevo.setApellidos(request.getApellidos());
		usuarioNuevo.setAutoridades(null);
		usuarioNuevo.setContrasena(passwordEncoder.encode(request.getContrasena()));
		usuarioNuevo.setCorreo(request.getCorreo());
		usuarioNuevo.setEsActivo(true);
		usuarioNuevo.setNombres(request.getNombres());
		usuarioNuevo.setNombreUsuario(request.getNombreUsuario());
		usuarioNuevo.setRol(null);
		
		usuarioJpaRepository.save(usuarioNuevo);
		
		// Devolver mensaje respuesta de usuario creado 
		return new UsuarioResponse();
		
	}

	private Boolean verificaPrecondiciones(@Valid UsuarioRequest request) {
		Optional<Usuario> byCorreo = usuarioJpaRepository.findByCorreo(request.getCorreo());
		Optional<Usuario> byUsername = usuarioJdbcRepository.findByUsername(request.getCorreo());
		
		if(byCorreo.isPresent() || byUsername.isPresent()) {
			return false;
		}
		
		return true;
		
	}	

}
