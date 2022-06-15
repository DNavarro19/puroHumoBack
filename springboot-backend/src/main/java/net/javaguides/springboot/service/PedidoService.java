package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.PedidoDTO;

public interface PedidoService {
	PedidoDTO saveOrUpdatePedido(PedidoDTO pedido);

	List<PedidoDTO> getAllPedidos();

	List<PedidoDTO> getAllPedidosByUsuarioID(long usuarioID);
	
	List<PedidoDTO> getAllPedidosPendientes();
	
	List<PedidoDTO> getAllPedidosPendientesByUsuarioID(long usuarioID);

	PedidoDTO getPedidoById(Long id) throws Exception;

	PedidoDTO deletePedido(Long id) throws Exception;
		
	PedidoDTO cancelarPedidoById(Long id) throws Exception;
}
