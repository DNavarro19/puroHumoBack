package net.javaguides.springboot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javassist.tools.rmi.ObjectNotFoundException;
import net.javaguides.springboot.dto.ProductoDTO;
import net.javaguides.springboot.model.Oferta;
import net.javaguides.springboot.model.Producto;
import net.javaguides.springboot.repository.ProductoRepository;
import net.javaguides.springboot.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

	private ProductoRepository productoRepository;
	private ModelMapper modelMapper;

	public ProductoServiceImpl(ProductoRepository productoRepository) {
		super();
		this.productoRepository = productoRepository;
		this.modelMapper = new ModelMapper();
	}

	@Override
	public ProductoDTO saveOrUpdateProducto(ProductoDTO producto) {

		ProductoDTO productoDto = null;

		if (producto != null && producto.getCategoriaID() != 0) {
			Producto productoModel = this.modelMapper.map(producto, Producto.class);

			if (productoModel.isActivo() == false) {
				productoModel.setActivo(Boolean.TRUE);
			}

			productoModel = productoRepository.save(productoModel);
			productoDto = this.modelMapper.map(productoModel, ProductoDTO.class);

		} else {
			//
		}
		return productoDto;
	}

	@Override
	public List<ProductoDTO> getAllProductos() {

		List<ProductoDTO> productosDto = new ArrayList<ProductoDTO>();

		List<Producto> productos = productoRepository.findAll();

		for (Producto producto : productos) {
			productosDto.add(this.modelMapper.map(producto, ProductoDTO.class));
		}

		return productosDto;

	}
	
	@Override
	public List<ProductoDTO> getAllProductosOfertas() {

		List<ProductoDTO> productosDto = new ArrayList<ProductoDTO>();
		
		List<Producto> productosAux = new ArrayList<Producto>();

		List<Producto> productos = productoRepository.findAll();

		for (Producto producto : productos) {
			if(producto.getOferta() != null)
				productosAux.add(producto);
		}
		
		for (Producto producto : productosAux) {
			productosDto.add(this.modelMapper.map(producto, ProductoDTO.class));
		}

		return productosDto;

	}

	@Override
	public List<ProductoDTO> getAllProductosActivos() {

		List<ProductoDTO> productosDto = new ArrayList<ProductoDTO>();

		List<Producto> productos = productoRepository.findAllByActivo(true);

		for (Producto producto : productos) {
			productosDto.add(this.modelMapper.map(producto, ProductoDTO.class));
		}

		return productosDto;

	}

	@Override
	public List<ProductoDTO> getAllProductosByOfertaID(long ofertaID) {

		List<ProductoDTO> productosDto = new ArrayList<ProductoDTO>();

		Oferta oferta = new Oferta();
		oferta.setId(ofertaID);

		List<Producto> productos = productoRepository.findAllByOferta(oferta);

		for (Producto producto : productos) {
			productosDto.add(this.modelMapper.map(producto, ProductoDTO.class));
		}

		return productosDto;
	}

	@Override
	public ProductoDTO getProductoById(Long id) throws Exception {

		ProductoDTO productoDto = null;

		if (id != 0) {

			Producto producto = productoRepository.getById(id);

			if (producto != null) {
				productoDto = this.modelMapper.map(producto, ProductoDTO.class);
			} else {
				throw new ObjectNotFoundException("No existe producto con el id: " + id);
			}
		} else {
			throw new Exception("Id del producto vacío");
		}

		return productoDto;
	}

	@Override
	public ProductoDTO deleteProducto(Long id) throws Exception {

		ProductoDTO productoDto = null;

		if (id != 0) {

			Optional<Producto> opProducto = productoRepository.findById(id);

			Producto productoModel = null;

			if (opProducto.isPresent()) {
				productoModel = opProducto.get();
				productoModel.setActivo(Boolean.FALSE);

				productoModel = productoRepository.save(productoModel);
				productoDto = this.modelMapper.map(productoModel, ProductoDTO.class);

			} else {
				throw new ObjectNotFoundException("Producto con id : " + id + " , no encontrado");
			}

		} else {
			throw new Exception("Producto vacío");
		}

		return productoDto;
	}

	@Override
	public ProductoDTO mostrarProducto(Long id) throws Exception {

		ProductoDTO productoDto = null;

		if (id != 0) {

			Optional<Producto> opProducto = productoRepository.findById(id);

			Producto productoModel = null;

			if (opProducto.isPresent()) {
				productoModel = opProducto.get();
				productoModel.setActivo(Boolean.TRUE);

				productoModel = productoRepository.save(productoModel);
				productoDto = this.modelMapper.map(productoModel, ProductoDTO.class);

			} else {
				throw new ObjectNotFoundException("Producto con id : " + id + " , no encontrado");
			}

		} else {
			throw new Exception("Producto vacío");
		}

		return productoDto;
	}

	@Override
	public List<ProductoDTO> setOfertaToList(List<Long> productosIDs, Long ofertaID) throws Exception {
		List<ProductoDTO> productos = new ArrayList<ProductoDTO>();

		for (Long productoID : productosIDs) {
			Optional<Producto> opProducto = productoRepository.findById(productoID);

			Producto productoModel = null;

			if (opProducto.isPresent()) {
				if (ofertaID == 0) {
					productoModel = opProducto.get();
					productoModel.setOferta(null);

					productoModel = productoRepository.save(productoModel);
					productos.add(this.modelMapper.map(productoModel, ProductoDTO.class));
				} else {
					Oferta ofertaModel = new Oferta();
					ofertaModel.setId(ofertaID);

					productoModel = opProducto.get();
					productoModel.setOferta(ofertaModel);

					productoModel = productoRepository.save(productoModel);
					productos.add(this.modelMapper.map(productoModel, ProductoDTO.class));
				}

			} else {
				throw new ObjectNotFoundException("Producto con id : " + productoID + " , no encontrado");
			}

		}

		return productos;
	}

	@Override
	public ProductoDTO setOferta(Long productoID, Long ofertaID) throws Exception {
		ProductoDTO productoDTO = new ProductoDTO();

		Optional<Producto> opProducto = productoRepository.findById(productoID);

		Producto productoModel = null;

		if (opProducto.isPresent()) {
			if (ofertaID == 0) {
				productoModel = opProducto.get();
				productoModel.setOferta(null);

				productoModel = productoRepository.save(productoModel);
				productoDTO = this.modelMapper.map(productoModel, ProductoDTO.class);
			} else {
				Oferta ofertaModel = new Oferta();
				ofertaModel.setId(ofertaID);

				productoModel = opProducto.get();
				productoModel.setOferta(ofertaModel);

				productoModel = productoRepository.save(productoModel);
				productoDTO = this.modelMapper.map(productoModel, ProductoDTO.class);
			}

		} else {
			throw new ObjectNotFoundException("Producto con id : " + productoID + " , no encontrado");
		}

		return productoDTO;
	}

}
