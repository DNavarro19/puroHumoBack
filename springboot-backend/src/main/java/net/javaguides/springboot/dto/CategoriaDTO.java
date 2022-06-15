package net.javaguides.springboot.dto;

import java.util.List;

import lombok.Data;

@Data
public class CategoriaDTO {

	private Long id;
	
	private String nombre;
	
	private boolean activo;
	
	private List<ProductoDTO> productos;
	
}
