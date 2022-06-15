package net.javaguides.springboot.security.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class NuevoUsuario {

	private String nombre;

	private String nombreUsuario;

	private String email;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fechaNacimiento;

	private String password;
	
	private String rol;

}
