package net.javaguides.springboot.dto;

import lombok.Data;

@Data
public class ProductoDTO {

	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	private Integer stock;
	
	private Double precio;
	
	private String imagen;
	
	private boolean activo;
	
	private Long categoriaID;
	
	private OfertaDTO oferta;

}
