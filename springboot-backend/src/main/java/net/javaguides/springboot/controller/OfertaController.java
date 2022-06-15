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

import net.javaguides.springboot.dto.OfertaDTO;
import net.javaguides.springboot.service.OfertaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ofertas")
public class OfertaController {

	private OfertaService ofertaService;

	public OfertaController(OfertaService ofertaService) {
		super();
		this.ofertaService = ofertaService;
	}

	// build create oferta REST API
	@PostMapping("/post")
	public ResponseEntity<OfertaDTO> saveOrUpdateOferta(@RequestBody OfertaDTO oferta) {
		return new ResponseEntity<OfertaDTO>(ofertaService.saveOrUpdateOferta(oferta), HttpStatus.CREATED);
	}

	// build get all ofertas REST API
	@GetMapping("/get")
	public List<OfertaDTO> getAllOfertas() {
		return ofertaService.getAllOfertas();
	}

	// build get oferta by id REST API
	// http://localhost:8080/api/ofertas/1
	@GetMapping("/get/{id}")
	public ResponseEntity<OfertaDTO> getOfertaById(@PathVariable("id") long ofertaId) throws Exception {
		return new ResponseEntity<OfertaDTO>(ofertaService.getOfertaById(ofertaId), HttpStatus.OK);
	}

	// build delete oferta REST API
	// http://localhost:8080/api/ofertas/1
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<OfertaDTO> deleteOferta(@PathVariable("id") long ofertaId) throws Exception {

		// delete oferta from DB
		return new ResponseEntity<OfertaDTO>(ofertaService.deleteOferta(ofertaId), HttpStatus.OK);
	}

}
