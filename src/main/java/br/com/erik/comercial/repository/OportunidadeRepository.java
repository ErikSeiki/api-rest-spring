package br.com.erik.comercial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erik.comercial.model.Oportunidade;

public interface OportunidadeRepository extends  JpaRepository<Oportunidade, Long>{
	
	Optional<Oportunidade> findByDescricaoAndNomeProspecto();

}
