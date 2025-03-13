package com.unam.dwb.auth.service.impl;

import java.util.Arrays;
import java.util.Date;
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
import com.unam.dwb.auth.util.Globales;

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

		if(!exitoPrecondiciones.booleanValue()) {
			UsuarioResponse usuarioExistenteResponse = new UsuarioResponse(null);
			usuarioExistenteResponse.setDetalles(Arrays.asList("Registro de usuario fallido. Usuario ya existente"));
			usuarioExistenteResponse.setFechaHora(Globales.formatDate(new Date()));
			usuarioExistenteResponse.setToken(null);
			usuarioExistenteResponse.setUsuario(null);
			return usuarioExistenteResponse;
		}	

		Usuario usuarioNuevo = new Usuario();
		usuarioNuevo.setApellidos(request.getApellidos());
		usuarioNuevo.setAutoridades(null);
		usuarioNuevo.setContrasena(passwordEncoder.encode(request.getContrasena()));
		usuarioNuevo.setCorreo(request.getCorreo());
		usuarioNuevo.setEsActivo(true);
		usuarioNuevo.setNombres(request.getNombres());
		usuarioNuevo.setNombreUsuario(request.getNombreUsuario());
		usuarioNuevo.setRol(null);

		Usuario usuario = usuarioJpaRepository.save(usuarioNuevo);
		
		UsuarioResponse usuarioCreadoResponse = new UsuarioResponse(null);
		usuarioCreadoResponse.setDetalles(Arrays.asList("Usuario creado exitosamente"));
		usuarioCreadoResponse.setFechaHora(Globales.formatDate(new Date()));
		usuarioCreadoResponse.setToken(null);
		usuarioCreadoResponse.setUsuario(usuario);
		
		log.info("Usuario registrado"); 
		return usuarioCreadoResponse;

	}

	private Boolean verificaPrecondiciones(@Valid UsuarioRequest request) {
		Optional<Usuario> byCorreo = usuarioJpaRepository.findByCorreo(request.getCorreo());
		Optional<Usuario> byUsername = usuarioJdbcRepository.findByUsername(request.getNombreUsuario());

		if(byCorreo.isPresent() || byUsername.isPresent()) {
			log.error("Usuario previamente registrado. No se puede registrar.");
			return false;
		}

		return true;

	}	

}
