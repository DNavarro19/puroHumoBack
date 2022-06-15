package net.javaguides.springboot.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import net.javaguides.springboot.security.entity.Usuario;

@Data
@Entity
@Table(name = "direccion")
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre_completo", nullable = false)
	private String nombreCompleto;
	
	@Column(name = "calle", nullable = false)
	private String calle;
	
	@Column(name = "numero", nullable = false)
	private String numero;

	@Column(name = "telefono", nullable = false)
	private String telefono;

	@Column(name = "codigo_postal", nullable = false)
	private String codigoPostal;

	@Column(name = "ciudad", nullable = false)
	private String ciudad;

	@Column(name = "provincia", nullable = false)
	private String provincia;

	@Column(name = "activo", nullable = false)
	private boolean activo;

	@Column(name = "predeterminada", nullable = false)
	private boolean predeterminada;

	@OneToMany(mappedBy = "direccion", cascade = CascadeType.ALL)
	private List<Pedido> pedidos;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

}
