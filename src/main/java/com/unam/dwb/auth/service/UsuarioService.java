package com.unam.dwb.auth.service;

import com.unam.dwb.auth.model.request.UsuarioRequest;
import com.unam.dwb.auth.model.response.UsuarioResponse;

import jakarta.validation.Valid;

public interface UsuarioService {
	
	public UsuarioResponse registraUsuario(@Valid UsuarioRequest request);

}
