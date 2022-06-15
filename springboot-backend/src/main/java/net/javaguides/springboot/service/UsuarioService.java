package net.javaguides.springboot.service;

import java.util.Optional;

import net.javaguides.springboot.security.entity.Usuario;

public interface UsuarioService {
	
	Usuario save(Usuario usuario);

	Optional<Usuario> getByNombreUsuario(String nombreUsuario);

	boolean existsByNombreUsuario(String nombreUsuario);

	boolean existsByEmail(String email);
}
