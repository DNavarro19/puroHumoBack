package net.javaguides.springboot.security.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.security.dto.JwtDto;
import net.javaguides.springboot.security.dto.LoginUsuario;
import net.javaguides.springboot.security.dto.NuevoUsuario;
import net.javaguides.springboot.security.dto.UsuarioDTO;
import net.javaguides.springboot.security.entity.Rol;
import net.javaguides.springboot.security.entity.Usuario;
import net.javaguides.springboot.security.enums.RolNombre;
import net.javaguides.springboot.security.jwt.JwtProvider;
import net.javaguides.springboot.security.service.RolService;
import net.javaguides.springboot.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	RolService rolService;

	@Autowired
	JwtProvider jwtProvider;

	ModelMapper modelMapper;

	public AuthController() {
		super();
		this.modelMapper = new ModelMapper();
	}

	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity("campos mal puestos o email inválido", HttpStatus.BAD_REQUEST);
		if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			return new ResponseEntity("ese nombre ya existe", HttpStatus.BAD_REQUEST);
		if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
			return new ResponseEntity("ese email ya existe", HttpStatus.BAD_REQUEST);
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
				nuevoUsuario.getEmail(), nuevoUsuario.getDni(), nuevoUsuario.getFechaNacimiento(),
				passwordEncoder.encode(nuevoUsuario.getPassword()));
		Rol rol = null;
		rol = new Rol(RolNombre.ROLE_USER);
		usuario.setRol(rol);
		usuario = usuarioService.save(usuario);
		UsuarioDTO usuarioDTO = this.modelMapper.map(usuario, UsuarioDTO.class);
		return new ResponseEntity(usuarioDTO, HttpStatus.CREATED);
	}

	@PostMapping("/nuevoAdmin")
	public ResponseEntity<?> nuevoAdmin(@RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity("campos mal puestos o email inválido", HttpStatus.BAD_REQUEST);
		if (usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
			return new ResponseEntity("ese nombre ya existe", HttpStatus.BAD_REQUEST);
		if (usuarioService.existsByEmail(nuevoUsuario.getEmail()))
			return new ResponseEntity("ese email ya existe", HttpStatus.BAD_REQUEST);
		Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
				nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
		Rol rol = null;
		rol = new Rol(RolNombre.ROLE_ADMIN);
		usuario.setRol(rol);
		usuario = usuarioService.save(usuario);
		UsuarioDTO usuarioDTO = this.modelMapper.map(usuario, UsuarioDTO.class);
		return new ResponseEntity(usuarioDTO, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@RequestBody LoginUsuario loginUsuario, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return new ResponseEntity("campos mal puestos", HttpStatus.BAD_REQUEST);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
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
