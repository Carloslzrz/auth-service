package com.unam.dwb.auth.service;

import com.unam.dwb.auth.model.request.AuthRequest;
import com.unam.dwb.auth.model.response.AuthResponse;
import jakarta.validation.Valid;

public interface AuthService {
	
	public AuthResponse autenticaUsuario(@Valid AuthRequest request);

}
