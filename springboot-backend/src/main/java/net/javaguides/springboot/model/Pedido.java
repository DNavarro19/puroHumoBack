package net.javaguides.springboot.model;

import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import net.javaguides.springboot.security.entity.Usuario;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "cancelado", nullable = false)
	private boolean cancelado;

	@Column(name = "entregado", nullable = false)
	private boolean entregado;

	@Column(name = "precio", nullable = false)
	private Double precio;
	
	@Column(name = "fecha_pedido", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date fechaPedido;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name = "fecha_entrega", nullable = true)
	private Date fechaEntrega;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "direccion_id", nullable = false)
	private Direccion direccion;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<PedidoProducto> pedidoProductos;

}
