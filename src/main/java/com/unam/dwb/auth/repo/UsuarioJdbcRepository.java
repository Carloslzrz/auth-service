package com.unam.dwb.auth.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.unam.dwb.auth.domain.Usuario;

@Repository
public class UsuarioJdbcRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public Optional<Usuario> findByUsername(String username) {
		String sql = "SELECT nombre_usuario, correo FROM dwb.usuario WHERE nombre_usuario = ?";
		
		List<Usuario> resultadoQuery = jdbcTemplate.query(sql, new Object[] {username},
				(rs, row) -> new Usuario(
						rs.getString("nombre_usuario"),
						rs.getString("correo")
						));
		
		return resultadoQuery.stream().findFirst();
		
		
	}
	

}
