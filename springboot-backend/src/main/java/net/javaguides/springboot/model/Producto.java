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

@Data
@Entity
@Table(name = "producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String nombre;

	@Column(name = "descripcion", nullable = false)
	private String descripcion;

	@Column(name = "stock", nullable = false)
	private Integer stock;

	@Column(name = "precio", nullable = false)
	private Double precio;

	@Column(name = "imagen", nullable = false)
	private String imagen;

	@Column(name = "activo", nullable = false)
	private boolean activo;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false)
	private Categoria categoria;

	@ManyToOne(optional = true)
	@JoinColumn(name = "oferta_id", nullable = true)
	private Oferta oferta;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<PedidoProducto> pedidoProductos;

}
