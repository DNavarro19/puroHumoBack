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

import net.javaguides.springboot.dto.CategoriaDTO;
import net.javaguides.springboot.service.CategoriaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

	private CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		super();
		this.categoriaService = categoriaService;
	}

	// build create categoria REST API
	@PostMapping("/post")
	public ResponseEntity<CategoriaDTO> saveOrUpdateCategoria(@RequestBody CategoriaDTO categoria) {
		return new ResponseEntity<CategoriaDTO>(categoriaService.saveOrUpdateCategoria(categoria), HttpStatus.CREATED);
	}

	// build get all categorias REST API
	@GetMapping("/get")
	public List<CategoriaDTO> getAllCategorias() {
		return categoriaService.getAllCategorias();
	}

	// build get categoria by id REST API
	// http://localhost:8080/api/categorias/1
	@GetMapping("/get/{id}")
	public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable("id") long categoriaId) throws Exception {
		return new ResponseEntity<CategoriaDTO>(categoriaService.getCategoriaById(categoriaId), HttpStatus.OK);
	}

	// build delete categoria REST API
	// http://localhost:8080/api/categorias/1
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CategoriaDTO> deleteCategoria(@PathVariable("id") long categoriaId) throws Exception {

		// delete categoria from DB
		return new ResponseEntity<CategoriaDTO>(categoriaService.deleteCategoria(categoriaId), HttpStatus.OK);
	}
	
	@GetMapping("/mostrar/{id}")
	public ResponseEntity<CategoriaDTO> mostrarCategoria(@PathVariable("id") long categoriaId) throws Exception {

		// delete categoria from DB
		return new ResponseEntity<CategoriaDTO>(categoriaService.mostrarCategoria(categoriaId), HttpStatus.OK);
	}

}
