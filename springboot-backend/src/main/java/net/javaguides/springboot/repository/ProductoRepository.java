package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Oferta;
import net.javaguides.springboot.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

	List<Producto> findAllByOferta(Oferta oferta);

	List<Producto> findAllByActivo(boolean activo);
}
