package net.javaguides.springboot.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
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
	
	@PostMapping("/cambiarPassword/{usuarioID}")
	public ResponseEntity<UsuarioDTO> cambiarPassword(@PathVariable("usuarioID") Long usuarioID, @RequestBody String password) {
		Usuario usuario = usuarioService.getByID(usuarioID).get();
		usuario.setPassword(passwordEncoder.encode(password));
		usuario = usuarioService.save(usuario);
		
		UsuarioDTO usuarioDTO = this.modelMapper.map(usuario, UsuarioDTO.class);
		return new ResponseEntity(usuarioDTO, HttpStatus.OK);
	}
	
	@PostMapping("/cambiarEmail/{usuarioID}")
	public ResponseEntity<UsuarioDTO> cambiarEmail(@PathVariable("usuarioID") Long usuarioID, @RequestBody String email) {
		Usuario usuario = usuarioService.getByID(usuarioID).get();
		usuario.setEmail(email);
		usuario = usuarioService.save(usuario);
		
		UsuarioDTO usuarioDTO = this.modelMapper.map(usuario, UsuarioDTO.class);
		return new ResponseEntity(usuarioDTO, HttpStatus.OK);
	}
	
	@PostMapping("/cambiarNombreUsuario/{usuarioID}")
	public ResponseEntity<UsuarioDTO> cambiarNombreUsuario(@PathVariable("usuarioID") Long usuarioID, @RequestBody String nombreUsuario) {
		Usuario usuario = usuarioService.getByID(usuarioID).get();
		usuario.setNombreUsuario(nombreUsuario);
		usuario = usuarioService.save(usuario);
		
		UsuarioDTO usuarioDTO = this.modelMapper.map(usuario, UsuarioDTO.class);
		return new ResponseEntity(usuarioDTO, HttpStatus.OK);
	}
	
	@GetMapping("/existeUsuario/{nombreUsuario}")
	public ResponseEntity<Boolean> existsUsuarioByNombreUsuario(@PathVariable("nombreUsuario") String nombreUsuario) {
		if (!usuarioService.existsByNombreUsuario(nombreUsuario))
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@GetMapping("/existeEmail/{email}")
	public ResponseEntity<Boolean> existsUsuarioByEmail(@PathVariable("email") String email) {
		if (!usuarioService.existsByEmail(email))
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
