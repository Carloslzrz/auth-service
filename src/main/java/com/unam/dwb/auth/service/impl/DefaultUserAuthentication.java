package com.unam.dwb.auth.service.impl;

import com.unam.dwb.auth.model.request.AuthRequest;
import com.unam.dwb.auth.model.response.AuthResponse;
import com.unam.dwb.auth.service.AuthService;

import jakarta.validation.Valid;

public class DefaultUserAuthentication implements AuthService {

	@Override
	public AuthResponse autenticaUsuario(@Valid AuthRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
