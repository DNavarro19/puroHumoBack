package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.ProductoDTO;

public interface ProductoService {
	ProductoDTO saveOrUpdateProducto(ProductoDTO producto);

	List<ProductoDTO> getAllProductos();
	
	List<ProductoDTO> getAllProductosOfertas();

	List<ProductoDTO> getAllProductosActivos();

	List<ProductoDTO> getAllProductosByOfertaID(long ofertaID);

	ProductoDTO getProductoById(Long id) throws Exception;

	ProductoDTO deleteProducto(Long id) throws Exception;

	ProductoDTO mostrarProducto(Long id) throws Exception;

	List<ProductoDTO> setOfertaToList(List<Long> productosIDs, Long ofertaID) throws Exception;
	
	ProductoDTO setOferta(Long productoID, Long ofertaID) throws Exception;
}
