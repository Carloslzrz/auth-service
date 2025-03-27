package com.unam.dwb.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unam.dwb.auth.domain.Usuario;
import com.unam.dwb.auth.repo.UsuarioJpaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultUserAuthentication implements UserDetailsService {
	
	@Autowired
	private UsuarioJpaRepository usuarioJpaRepository;

	@Override
	public Usuario loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioJpaRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuario inexistente"));
		
		log.info("Usuario existente: {}", usuario);
		
		return usuario;
	}

}
