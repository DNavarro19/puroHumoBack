package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.OfertaDTO;

public interface OfertaService {
	OfertaDTO saveOrUpdateOferta(OfertaDTO oferta);

	List<OfertaDTO> getAllOfertas();

	OfertaDTO getOfertaById(Long id) throws Exception;
	
	OfertaDTO deleteOferta(Long id) throws Exception;
}
