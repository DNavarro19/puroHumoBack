package net.javaguides.springboot.security.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import net.javaguides.springboot.dto.DireccionDTO;
import net.javaguides.springboot.dto.PedidoDTO;

@Data
public class UsuarioDTO {
	
	private Long id;

	private String nombre;
	
	private String nombreUsuario;
	
	private String email;
	
	private String dni;
	
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date fechaNacimiento;
	
	private List<PedidoDTO> pedidos;
	
	private List<DireccionDTO> direcciones;
}
