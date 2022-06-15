package net.javaguides.springboot.dto;

import lombok.Data;

@Data
public class DireccionDTO {

	private Long id;
	
	private String nombreCompleto;
	
	private String calle;
	
	private String numero;
	
	private String telefono;
	
	private String codigoPostal;
	
	private String ciudad;
	
	private String provincia;
	
	private boolean activo;
	
	private boolean predeterminada;
	
	private Long usuarioID;
	
}
