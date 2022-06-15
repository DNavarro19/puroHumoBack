package net.javaguides.springboot.security.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import net.javaguides.springboot.model.Direccion;
import net.javaguides.springboot.model.Pedido;

@Data
@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre;

	@Column(unique = true)
	private String nombreUsuario;

	@Column(unique = true)
	private String email;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fechaNacimiento;

	private String password;

	@OneToMany(mappedBy = "usuario")
	private List<Pedido> pedidos;

	@OneToMany(mappedBy = "usuario")
	private List<Direccion> direcciones;

	@ManyToOne
	@JoinColumn(name = "rol_id")
	private Rol rol;

	public Usuario() {
	}

	public Usuario(@NotNull String nombre, @NotNull String nombreUsuario, @NotNull String email,
			@NotNull Date fechaNacimiento, @NotNull String password) {
		this.nombre = nombre;
		this.nombreUsuario = nombreUsuario;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.password = password;
	}

}
