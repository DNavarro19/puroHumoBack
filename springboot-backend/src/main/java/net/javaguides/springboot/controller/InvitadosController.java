package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.dto.CategoriaDTO;
import net.javaguides.springboot.dto.ProductoDTO;
import net.javaguides.springboot.service.CategoriaService;
import net.javaguides.springboot.service.ProductoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/invitados")
public class InvitadosController {

	private ProductoService productoService;
	private CategoriaService categoriaService;

	public InvitadosController(ProductoService productoService, CategoriaService categoriaService) {
		super();
		this.productoService = productoService;
		this.categoriaService = categoriaService;
	}

	// build get all productos REST API
	@GetMapping("/productos")
	public List<ProductoDTO> getAllProductos() {
		return productoService.getAllProductos();
	}

	// build get producto by id REST API
	// http://localhost:8080/api/productos/1
	@GetMapping("/productos/{id}")
	public ResponseEntity<ProductoDTO> getProductoById(@PathVariable("id") Long productoId) throws Exception {
		return new ResponseEntity<ProductoDTO>(productoService.getProductoById(productoId), HttpStatus.OK);
	}
	
	// build get all categorias REST API
		@GetMapping("/categorias")
		public List<CategoriaDTO> getAllCategorias() {
			return categoriaService.getAllCategorias();
		}

		// build get categoria by id REST API
		// http://localhost:8080/api/categorias/1
		@GetMapping("/categorias/{id}")
		public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable("id") Long categoriaId) throws Exception {
			return new ResponseEntity<CategoriaDTO>(categoriaService.getCategoriaById(categoriaId), HttpStatus.OK);
		}

}
