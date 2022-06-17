package net.javaguides.springboot.dto;

import lombok.Data;

@Data
public class PedidoProductoDTO {

	private Long id;
	
	private Long pedidoID;
	
	private ProductoDTO producto;
	
	private Integer cantidad;
	
	private Double precio;
	
}
