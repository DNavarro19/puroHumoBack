package net.javaguides.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javassist.tools.rmi.ObjectNotFoundException;
import net.javaguides.springboot.dto.DireccionDTO;
import net.javaguides.springboot.model.Direccion;
import net.javaguides.springboot.repository.DireccionRepository;
import net.javaguides.springboot.security.entity.Usuario;
import net.javaguides.springboot.service.DireccionService;

@Service
public class DireccionServiceImpl implements DireccionService {

	private DireccionRepository direccionRepository;
	private ModelMapper modelMapper;

	public DireccionServiceImpl(DireccionRepository direccionRepository) {
		super();
		this.direccionRepository = direccionRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public DireccionDTO saveOrUpdateDireccion(DireccionDTO direccion) {

		DireccionDTO direccionDto = null;

		if (direccion != null && direccion.getUsuarioID() != 0) {
			Direccion direccionModel = this.modelMapper.map(direccion, Direccion.class);

			if (direccionModel.isActivo() == false) {
				direccionModel.setActivo(Boolean.TRUE);
			}

			direccionModel = direccionRepository.save(direccionModel);
			direccionDto = this.modelMapper.map(direccionModel, DireccionDTO.class);

		} else {
			//
		}
		return direccionDto;
	}

	@Override
	public List<DireccionDTO> getAllDirecciones() {

		List<DireccionDTO> direccionesDto = new ArrayList<DireccionDTO>();

		List<Direccion> direcciones = direccionRepository.findAll();

		for (Direccion direccion : direcciones) {
			direccionesDto.add(this.modelMapper.map(direccion, DireccionDTO.class));
		}

		return direccionesDto;
	}
	
	@Override
	public List<DireccionDTO> getAllDireccionesByUsuarioID(long usuarioID) {

		List<DireccionDTO> direccionesDto = new ArrayList<DireccionDTO>();
		
		Usuario usuario = new Usuario();
		usuario.setId(usuarioID);

		List<Direccion> direcciones = direccionRepository.findAllByUsuario(usuario);

		for (Direccion direccion : direcciones) {
			direccionesDto.add(this.modelMapper.map(direccion, DireccionDTO.class));
		}

		return direccionesDto;
	}

	@Override
	public DireccionDTO getDireccionById(Long id) throws Exception {

		DireccionDTO direccionDto = null;

		if (id != 0) {

			Direccion direccion = direccionRepository.getById(id);

			if (direccion != null) {
				direccionDto = this.modelMapper.map(direccion, DireccionDTO.class);
			} else {
				throw new ObjectNotFoundException("No existe direccion con el id: " + id);
			}
		} else {
			throw new Exception("Id del direccion vacío");
		}

		return direccionDto;
	}

	@Override
	public DireccionDTO deleteDireccion(Long id) throws Exception {

		DireccionDTO direccionDto = null;

		if (id != 0) {

			Optional<Direccion> opDireccion = direccionRepository.findById(id);

			Direccion direccionModel = null;

			if (opDireccion.isPresent()) {
				direccionModel = opDireccion.get();
				direccionModel.setActivo(Boolean.FALSE);

				direccionModel = direccionRepository.save(direccionModel);
				direccionDto = this.modelMapper.map(direccionModel, DireccionDTO.class);

			} else {
				throw new ObjectNotFoundException("Direccion con id : " + id + " , no encontrado");
			}

		} else {
			throw new Exception("Direccion vacío");
		}

		return direccionDto;
	}

}
