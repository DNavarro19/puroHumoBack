package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

}
