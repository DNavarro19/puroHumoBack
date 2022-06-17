package net.javaguides.springboot.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "pedido_producto")
public class PedidoProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@Column(nullable = false)
	private Integer cantidad;
	
	@Column(nullable = false)
	private Double precio;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PedidoProducto))
			return false;
		PedidoProducto that = (PedidoProducto) o;
		return Objects.equals(pedido.getId(), that.pedido.getId())
				&& Objects.equals(producto.getNombre(), that.producto.getNombre())
				&& Objects.equals(cantidad, that.cantidad);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pedido.getId(), producto.getNombre(), cantidad);
	}
}
