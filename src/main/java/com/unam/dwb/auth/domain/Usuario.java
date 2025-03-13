package com.unam.dwb.auth.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@JsonInclude(Include.NON_EMPTY)
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 3859609465552592790L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;
	
	private String nombres;
	private String apellidos;

	@Column(unique = true)
	private String nombreUsuario;
	
	@Column(unique = true)
	private String correo;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena;
	
	private String rol;
    private List<String> autoridades;
    
    private Boolean esActivo;
    
    public Usuario(String nombreUsuario, String correo) {
    	this.nombreUsuario = nombreUsuario;
    	this.correo = correo;
    }

}
