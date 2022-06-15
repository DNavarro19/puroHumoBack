package net.javaguides.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javassist.tools.rmi.ObjectNotFoundException;
import net.javaguides.springboot.dto.CategoriaDTO;
import net.javaguides.springboot.model.Categoria;
import net.javaguides.springboot.repository.CategoriaRepository;
import net.javaguides.springboot.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaRepository categoriaRepository;
	private ModelMapper modelMapper;

	public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public CategoriaDTO saveOrUpdateCategoria(CategoriaDTO categoria) {

		CategoriaDTO categoriaDto = null;

		if (categoria != null) {
			Categoria categoriaModel = this.modelMapper.map(categoria, Categoria.class);

			if (categoriaModel.isActivo() == false) {
				categoriaModel.setActivo(Boolean.TRUE);
			}

			categoriaModel = categoriaRepository.save(categoriaModel);
			categoriaDto = this.modelMapper.map(categoriaModel, CategoriaDTO.class);

		} else {
			//
		}
		return categoriaDto;
	}

	@Override
	public List<CategoriaDTO> getAllCategorias() {

		List<CategoriaDTO> categoriasDto = new ArrayList<CategoriaDTO>();

		List<Categoria> categorias = categoriaRepository.findAll();

		for (Categoria categoria : categorias) {
			categoriasDto.add(this.modelMapper.map(categoria, CategoriaDTO.class));
		}

		return categoriasDto;

	}

	@Override
	public CategoriaDTO getCategoriaById(Long id) throws Exception {

		CategoriaDTO categoriaDto = null;

		if (id != 0) {

			Categoria categoria = categoriaRepository.getById(id);

			if (categoria != null) {
				categoriaDto = this.modelMapper.map(categoria, CategoriaDTO.class);
			} else {
				throw new ObjectNotFoundException("No existe categoria con el id: " + id);
			}
		} else {
			throw new Exception("Id del categoria vacío");
		}

		return categoriaDto;
	}

	@Override
	public CategoriaDTO deleteCategoria(Long id) throws Exception {

		CategoriaDTO categoriaDto = null;

		if (id != 0) {

			Optional<Categoria> opCategoria = categoriaRepository.findById(id);

			Categoria categoriaModel = null;

			if (opCategoria.isPresent()) {
				categoriaModel = opCategoria.get();
				categoriaModel.setActivo(Boolean.FALSE);

				categoriaModel = categoriaRepository.save(categoriaModel);
				categoriaDto = this.modelMapper.map(categoriaModel, CategoriaDTO.class);

			} else {
				throw new ObjectNotFoundException("Categoria con id : " + id + " , no encontrado");
			}

		} else {
			throw new Exception("Categoria vacío");
		}

		return categoriaDto;
	}
	
	@Override
	public CategoriaDTO mostrarCategoria(Long id) throws Exception {

		CategoriaDTO categoriaDto = null;

		if (id != 0) {

			Optional<Categoria> opCategoria = categoriaRepository.findById(id);

			Categoria categoriaModel = null;

			if (opCategoria.isPresent()) {
				categoriaModel = opCategoria.get();
				categoriaModel.setActivo(Boolean.TRUE);

				categoriaModel = categoriaRepository.save(categoriaModel);
				categoriaDto = this.modelMapper.map(categoriaModel, CategoriaDTO.class);

			} else {
				throw new ObjectNotFoundException("Categoria con id : " + id + " , no encontrado");
			}

		} else {
			throw new Exception("Categoria vacío");
		}

		return categoriaDto;
	}

}
