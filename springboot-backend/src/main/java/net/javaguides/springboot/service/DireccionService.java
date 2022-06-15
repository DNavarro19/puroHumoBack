package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.DireccionDTO;

public interface DireccionService {
	DireccionDTO saveOrUpdateDireccion(DireccionDTO direccion);

	List<DireccionDTO> getAllDirecciones();
	
	List<DireccionDTO> getAllDireccionesByUsuarioID(long usuarioID);

	DireccionDTO getDireccionById(Long id) throws Exception;

	DireccionDTO deleteDireccion(Long id) throws Exception;
}
