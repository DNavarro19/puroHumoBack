package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.CategoriaDTO;

public interface CategoriaService {
	CategoriaDTO saveOrUpdateCategoria(CategoriaDTO categoria);

	List<CategoriaDTO> getAllCategorias();

	CategoriaDTO getCategoriaById(Long id) throws Exception;

	CategoriaDTO deleteCategoria(Long id) throws Exception;
	
	CategoriaDTO mostrarCategoria(Long id) throws Exception;
}
