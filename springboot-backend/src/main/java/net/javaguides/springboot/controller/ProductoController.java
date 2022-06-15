package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.dto.ProductoDTO;
import net.javaguides.springboot.service.ProductoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		super();
		this.productoService = productoService;
	}

	// build create producto REST API
	@PostMapping("/post")
	public ResponseEntity<ProductoDTO> saveOrUpdateProducto(@RequestBody ProductoDTO producto) {
		return new ResponseEntity<ProductoDTO>(productoService.saveOrUpdateProducto(producto), HttpStatus.CREATED);
	}

	@PostMapping("/setOfertaToList/{id}")
	public List<ProductoDTO> setOfertaToList(@RequestBody List<Long> productosIDs, @PathVariable("id") long ofertaID)
			throws Exception {
		return productoService.setOfertaToList(productosIDs, ofertaID);
	}

	@PostMapping("/setOferta/{id}")
	public ProductoDTO setOfertaToList(@RequestBody Long productoID, @PathVariable("id") long ofertaID)
			throws Exception {
		return productoService.setOferta(productoID, ofertaID);
	}

	// build get all productos REST API
	@GetMapping("/get")
	public List<ProductoDTO> getAllProductos() {
		return productoService.getAllProductos();
	}

	@GetMapping("/get/activos")
	public List<ProductoDTO> getAllProductosActivos() {
		return productoService.getAllProductosActivos();
	}

	@GetMapping("/get/oferta/{id}")
	public List<ProductoDTO> getAllProductosByOfertaID(@PathVariable("id") long ofertaID) {
		return productoService.getAllProductosByOfertaID(ofertaID);
	}

	// build get producto by id REST API
	// http://localhost:8080/api/productos/1
	@GetMapping("/get/{id}")
	public ResponseEntity<ProductoDTO> getProductoById(@PathVariable("id") long productoId) throws Exception {
		return new ResponseEntity<ProductoDTO>(productoService.getProductoById(productoId), HttpStatus.OK);
	}

	// build delete producto REST API
	// http://localhost:8080/api/productos/1
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ProductoDTO> deleteProducto(@PathVariable("id") long productoId) throws Exception {

		// delete producto from DB
		return new ResponseEntity<ProductoDTO>(productoService.deleteProducto(productoId), HttpStatus.OK);
	}

	@GetMapping("/mostrar/{id}")
	public ResponseEntity<ProductoDTO> mostrarProducto(@PathVariable("id") long productoId) throws Exception {

		// delete producto from DB
		return new ResponseEntity<ProductoDTO>(productoService.mostrarProducto(productoId), HttpStatus.OK);
	}

}
