package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Direccion;
import net.javaguides.springboot.security.entity.Usuario;

public interface DireccionRepository extends JpaRepository<Direccion, Long> {
	List<Direccion> findAllByUsuario(Usuario usuario);
	
}
