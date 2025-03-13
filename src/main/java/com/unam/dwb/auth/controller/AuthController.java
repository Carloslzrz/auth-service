package com.unam.dwb.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unam.dwb.auth.model.request.AuthRequest;
import com.unam.dwb.auth.model.response.AuthResponse;
import com.unam.dwb.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name= "Autenticación de usuarios registrados", description = "Operaciones para autenticar usuarios que existen en el sistema")
public class AuthController {

	private static final String REQUEST_LOG = "Request: {}";
	private static final String RESPONSE_LOG = "Response: {}";
	private static final String ENDPOINT_AUTH_USER = "/auth";
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value = ENDPOINT_AUTH_USER)
	@Operation(summary = "Autentica un usuario", description = "Autentica un usuario y devuelve un JWT con el ROL registrado si las credenciales son válidas")
	public ResponseEntity<AuthResponse> autenticaUsuario(@Valid @RequestBody AuthRequest request)  {
		log.info("AuthController.autenticaUsuario() - In");
		
		if(log.isDebugEnabled())
			log.debug(REQUEST_LOG, request);
		
		AuthResponse response = authService.autenticaUsuario(request);
		
		if(log.isDebugEnabled())
			log.debug(RESPONSE_LOG);
		log.info("AuthController.autenticaUsuario() - Out");
		
		return new ResponseEntity<>(response, HttpStatus.OK);      
	}

}
