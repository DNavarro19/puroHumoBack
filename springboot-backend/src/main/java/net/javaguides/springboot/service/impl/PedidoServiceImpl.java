package net.javaguides.springboot.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javassist.tools.rmi.ObjectNotFoundException;
import net.javaguides.springboot.dto.PedidoDTO;
import net.javaguides.springboot.model.Pedido;
import net.javaguides.springboot.model.PedidoProducto;
import net.javaguides.springboot.model.Producto;
import net.javaguides.springboot.repository.PedidoRepository;
import net.javaguides.springboot.security.entity.Usuario;
import net.javaguides.springboot.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	private PedidoRepository pedidoRepository;
	private ModelMapper modelMapper;

	public PedidoServiceImpl(PedidoRepository pedidoRepository) {
		super();
		this.pedidoRepository = pedidoRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public PedidoDTO saveOrUpdatePedido(PedidoDTO pedido) {

		PedidoDTO pedidoDto = null;

		if (pedido != null && pedido.getUsuarioID() != 0) {
			Pedido pedidoModel = this.modelMapper.map(pedido, Pedido.class);

			pedidoModel = pedidoRepository.save(pedidoModel);
			pedidoDto = this.modelMapper.map(pedidoModel, PedidoDTO.class);

		} else {
			//
		}
		return pedidoDto;
	}

	@Override
	public List<PedidoDTO> getAllPedidos() {

		List<PedidoDTO> pedidosDto = new ArrayList<PedidoDTO>();

		List<Pedido> pedidos = pedidoRepository.findAll();

		for (Pedido pedido : pedidos) {
			pedidosDto.add(this.modelMapper.map(pedido, PedidoDTO.class));
		}

		return pedidosDto;

	}

	@Override
	public List<PedidoDTO> getAllPedidosByUsuarioID(long usuarioID) {

		List<PedidoDTO> pedidosDto = new ArrayList<PedidoDTO>();

		Usuario usuario = new Usuario();
		usuario.setId(usuarioID);

		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(usuario);

		for (Pedido pedido : pedidos) {
			pedidosDto.add(this.modelMapper.map(pedido, PedidoDTO.class));
		}

		return pedidosDto;
	}
	
	@Override
	public List<PedidoDTO> getAllPedidosPendientes() {

		List<PedidoDTO> pedidosDto = new ArrayList<PedidoDTO>();

		List<Pedido> pedidos = pedidoRepository.findAllByCanceladoAndEntregado(false, false);

		for (Pedido pedido : pedidos) {
			pedidosDto.add(this.modelMapper.map(pedido, PedidoDTO.class));
		}

		return pedidosDto;
	}
	
	@Override
	public List<PedidoDTO> getAllPedidosPendientesByUsuarioID(long usuarioID) {

		List<PedidoDTO> pedidosDto = new ArrayList<PedidoDTO>();

		Usuario usuario = new Usuario();
		usuario.setId(usuarioID);

		List<Pedido> pedidos = pedidoRepository.findAllByUsuarioAndCanceladoAndEntregado(usuario, false, false);

		for (Pedido pedido : pedidos) {
			pedidosDto.add(this.modelMapper.map(pedido, PedidoDTO.class));
		}

		return pedidosDto;
	}

	@Override
	public PedidoDTO getPedidoById(Long id) throws Exception {

		PedidoDTO pedidoDto = null;

		if (id != 0) {

			Pedido pedido = pedidoRepository.getById(id);

			if (pedido != null) {
				pedidoDto = this.modelMapper.map(pedido, PedidoDTO.class);
			} else {
				throw new ObjectNotFoundException("No existe pedido con el id: " + id);
			}
		} else {
			throw new Exception("Id del pedido vacío");
		}

		return pedidoDto;
	}

	@Override
	public PedidoDTO deletePedido(Long id) throws Exception {

		PedidoDTO pedidoDto = null;

		if (id != 0) {

			Optional<Pedido> opPedido = pedidoRepository.findById(id);

			Pedido pedidoModel = null;

			if (opPedido.isPresent()) {
				pedidoModel = opPedido.get();
				pedidoModel.setCancelado(Boolean.FALSE);

				pedidoModel = pedidoRepository.save(pedidoModel);
				pedidoDto = this.modelMapper.map(pedidoModel, PedidoDTO.class);

			} else {
				throw new ObjectNotFoundException("Pedido con id : " + id + " , no encontrado");
			}

		} else {
			throw new Exception("Pedido vacío");
		}

		return pedidoDto;
	}
	
	@Override
	public PedidoDTO cancelarPedidoById(Long id) throws Exception {

		PedidoDTO pedidoDto = null;

		if (id != 0) {

			Optional<Pedido> opPedido = pedidoRepository.findById(id);

			Pedido pedidoModel = null;

			if (opPedido.isPresent()) {
				pedidoModel = opPedido.get();
				pedidoModel.setCancelado(Boolean.TRUE);

				pedidoModel = pedidoRepository.save(pedidoModel);
				pedidoDto = this.modelMapper.map(pedidoModel, PedidoDTO.class);

			} else {
				throw new ObjectNotFoundException("Pedido con id : " + id + " , no encontrado");
			}

		} else {
			throw new Exception("Pedido vacío");
		}

		return pedidoDto;
	}

}
