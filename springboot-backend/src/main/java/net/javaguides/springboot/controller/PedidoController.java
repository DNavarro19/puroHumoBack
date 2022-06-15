package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.dto.PedidoDTO;
import net.javaguides.springboot.service.PedidoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	private PedidoService pedidoService;

	public PedidoController(PedidoService pedidoService) {
		super();
		this.pedidoService = pedidoService;
	}

	// build create pedido REST API
	@PostMapping("/post")
	public ResponseEntity<PedidoDTO> saveOrUpdatePedido(@RequestBody PedidoDTO pedido) {
		return new ResponseEntity<PedidoDTO>(pedidoService.saveOrUpdatePedido(pedido), HttpStatus.CREATED);
	}

	// build get all pedidos REST API
	@GetMapping("/get")
	public List<PedidoDTO> getAllPedidos() {
		return pedidoService.getAllPedidos();
	}

	@GetMapping("/get/usuario/{id}")
	public List<PedidoDTO> getAllPedidosByUsuarioID(@PathVariable("id") long usuarioID) {
		return pedidoService.getAllPedidosByUsuarioID(usuarioID);
	}

	@GetMapping("/get/pendientes")
	public List<PedidoDTO> getAllPedidosPendientes() {
		return pedidoService.getAllPedidosPendientes();
	}

	@GetMapping("/get/usuarioPendientes/{id}")
	public List<PedidoDTO> getAllPedidosPendientesByUsuarioID(@PathVariable("id") long usuarioID) {
		return pedidoService.getAllPedidosPendientesByUsuarioID(usuarioID);
	}

	// build get pedido by id REST API
	// http://localhost:8080/api/pedidos/1
	@GetMapping("/get/{id}")
	public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable("id") long pedidoId) throws Exception {
		return new ResponseEntity<PedidoDTO>(pedidoService.getPedidoById(pedidoId), HttpStatus.OK);
	}

	@GetMapping("/cancelar/{id}")
	public ResponseEntity<PedidoDTO> cancelarPedidoById(@PathVariable("id") long pedidoId) throws Exception {
		return new ResponseEntity<PedidoDTO>(pedidoService.cancelarPedidoById(pedidoId), HttpStatus.OK);
	}

	// build delete pedido REST API
	// http://localhost:8080/api/pedidos/1
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PedidoDTO> deletePedido(@PathVariable("id") long pedidoId) throws Exception {

		// delete pedido from DB
		return new ResponseEntity<PedidoDTO>(pedidoService.deletePedido(pedidoId), HttpStatus.OK);
	}

}
