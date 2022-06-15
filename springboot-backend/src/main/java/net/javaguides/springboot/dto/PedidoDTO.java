package net.javaguides.springboot.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class PedidoDTO {

	private Long id;
	
	private boolean cancelado;
	
	private boolean entregado;
	
	private Double precio;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fechaPedido;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date fechaEntrega;
	
	private Long usuarioID;
	
	private DireccionDTO direccion;
	
	private List<PedidoProductoDTO> pedidoProductos;
	
}
