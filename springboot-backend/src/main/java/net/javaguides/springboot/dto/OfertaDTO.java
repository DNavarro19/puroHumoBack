package net.javaguides.springboot.dto;

import java.util.List;

import lombok.Data;

@Data
public class OfertaDTO {

	private Long id;
	
	private Integer descuento;
			
	private List<Long> productosIDs;
}
