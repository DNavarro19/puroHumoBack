package net.javaguides.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Pedido;
import net.javaguides.springboot.security.entity.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	List<Pedido> findAllByUsuario(Usuario usuario);
	
	List<Pedido> findAllByUsuarioAndCanceladoAndEntregado(Usuario usuario, boolean cancelado, boolean entregado);

	List<Pedido> findAllByCanceladoAndEntregado(boolean cancelado, boolean entregado);

}
