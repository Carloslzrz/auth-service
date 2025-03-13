package com.unam.dwb.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unam.dwb.auth.model.request.UsuarioRequest;
import com.unam.dwb.auth.model.response.UsuarioResponse;
import com.unam.dwb.auth.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@Tag(name= "Administración de Usuarios", description = "Operaciones relevantes para el manejo de los usuarios de los microservicios del sistema")
public class UsuarioController {

	private static final String REQUEST_LOG = "Request: {}";
	private static final String RESPONSE_LOG = "Response: {}";
	private static final String ENDPOINT_REGISTRO = "/registro";
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping(value = ENDPOINT_REGISTRO)
	@Operation(summary = "Crea un usuario", description = "Endpoint que permite la creación o actualización de un usuario")
	public ResponseEntity<UsuarioResponse> upsertUsuario(@Valid @RequestBody UsuarioRequest request)  {
		log.info("UsuarioController.upsertUsuario() - In");
		
		if(log.isDebugEnabled())
			log.debug(REQUEST_LOG, request);
		
		UsuarioResponse response = usuarioService.registraUsuario(request);
		
		if(log.isDebugEnabled())
			log.debug(RESPONSE_LOG);
		log.info("UsuarioController.upsertUsuario() - Out");
		
		return new ResponseEntity<>(response, HttpStatus.OK);      
	}

}
