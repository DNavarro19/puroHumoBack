package net.javaguides.springboot.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.security.dto.UsuarioDTO;
import net.javaguides.springboot.security.entity.Usuario;
import net.javaguides.springboot.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private UsuarioService usuarioService;
	private ModelMapper modelMapper;
	
	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
		this.modelMapper = new ModelMapper();
	}

	@GetMapping("/{nombreUsuario}")
	public ResponseEntity<UsuarioDTO> getUsuarioByNombreUsuario(@PathVariable("nombreUsuario") String nombreUsuario) {
		if (!usuarioService.existsByNombreUsuario(nombreUsuario))
			return new ResponseEntity("No existe", HttpStatus.NOT_FOUND);
		Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
		UsuarioDTO usuarioDTO = this.modelMapper.map(usuario, UsuarioDTO.class);
		return new ResponseEntity(usuarioDTO, HttpStatus.OK);
	}

}
