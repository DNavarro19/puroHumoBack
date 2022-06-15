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

import net.javaguides.springboot.dto.DireccionDTO;
import net.javaguides.springboot.service.DireccionService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

	private DireccionService direccionService;

	public DireccionController(DireccionService direccionService) {
		super();
		this.direccionService = direccionService;
	}

	// build create direccion REST API
	@PostMapping("/post")
	public ResponseEntity<DireccionDTO> saveOrUpdateDireccion(@RequestBody DireccionDTO direccion) {
		return new ResponseEntity<DireccionDTO>(direccionService.saveOrUpdateDireccion(direccion), HttpStatus.CREATED);
	}

	// build get all direcciones REST API
	@GetMapping("/get")
	public List<DireccionDTO> getAllDirecciones() {
		return direccionService.getAllDirecciones();
	}
	
	@GetMapping("/get/usuario/{id}")
	public List<DireccionDTO> getAllDireccionesByUsuarioID(@PathVariable("id") long usuarioID) {
		return direccionService.getAllDireccionesByUsuarioID(usuarioID);
	}

	// build get direccion by id REST API
	// http://localhost:8080/api/direcciones/1
	@GetMapping("/get/{id}")
	public ResponseEntity<DireccionDTO> getDireccionById(@PathVariable("id") long direccionId) throws Exception {
		return new ResponseEntity<DireccionDTO>(direccionService.getDireccionById(direccionId), HttpStatus.OK);
	}

	// build delete direccion REST API
	// http://localhost:8080/api/direcciones/1
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<DireccionDTO> deleteDireccion(@PathVariable("id") long direccionId) throws Exception {

		// delete direccion from DB
		return new ResponseEntity<DireccionDTO>(direccionService.deleteDireccion(direccionId), HttpStatus.OK);
	}

}
