package net.javaguides.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javassist.tools.rmi.ObjectNotFoundException;
import net.javaguides.springboot.dto.OfertaDTO;
import net.javaguides.springboot.model.Oferta;
import net.javaguides.springboot.repository.OfertaRepository;
import net.javaguides.springboot.service.OfertaService;

@Service
public class OfertaServiceImpl implements OfertaService {

	private OfertaRepository ofertaRepository;
	private ModelMapper modelMapper;

	public OfertaServiceImpl(OfertaRepository ofertaRepository) {
		super();
		this.ofertaRepository = ofertaRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public OfertaDTO saveOrUpdateOferta(OfertaDTO oferta) {

		OfertaDTO ofertaDto = null;

		if (oferta != null) {
			Oferta ofertaModel = this.modelMapper.map(oferta, Oferta.class);

			ofertaModel = ofertaRepository.save(ofertaModel);
			ofertaDto = this.modelMapper.map(ofertaModel, OfertaDTO.class);
		} else {
			//
		}
		return ofertaDto;
	}

	@Override
	public List<OfertaDTO> getAllOfertas() {

		List<OfertaDTO> ofertasDto = new ArrayList<OfertaDTO>();

		List<Oferta> ofertas = ofertaRepository.findAll();

		for (Oferta oferta : ofertas) {
			OfertaDTO ofertaDto = this.modelMapper.map(oferta, OfertaDTO.class);
			List<Long> productosIDs = new ArrayList<Long>();
			oferta.getProductos().forEach(t -> productosIDs.add(t.getId()));
			ofertaDto.setProductosIDs(productosIDs);
			ofertasDto.add(ofertaDto);
		}

		return ofertasDto;

	}

	@Override
	public OfertaDTO getOfertaById(Long id) throws Exception {

		OfertaDTO ofertaDto = null;

		if (id != 0) {

			Oferta oferta = ofertaRepository.getById(id);

			if (oferta != null) {
				ofertaDto = this.modelMapper.map(oferta, OfertaDTO.class);
				List<Long> productosIDs = new ArrayList<Long>();
				oferta.getProductos().forEach(t -> productosIDs.add(t.getId()));
				ofertaDto.setProductosIDs(productosIDs);
			} else {
				throw new ObjectNotFoundException("No existe oferta con el id: " + id);
			}
		} else {
			throw new Exception("Id del oferta vacío");
		}

		return ofertaDto;
	}

	@Override
	public OfertaDTO deleteOferta(Long id) throws Exception {

		OfertaDTO ofertaDto = null;

		if (id != 0) {

			Optional<Oferta> opOferta = ofertaRepository.findById(id);

			Oferta ofertaModel = null;

			if (opOferta.isPresent()) {
				ofertaModel = opOferta.get();

				ofertaRepository.delete(ofertaModel);
				ofertaDto = this.modelMapper.map(ofertaModel, OfertaDTO.class);

			} else {
				throw new ObjectNotFoundException("Oferta con id : " + id + " , no encontrado");
			}

		} else {
			throw new Exception("Oferta vacío");
		}

		return ofertaDto;
	}

}
