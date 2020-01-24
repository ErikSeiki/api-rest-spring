package br.com.erik.comercial.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.erik.comercial.model.Oportunidade;
import br.com.erik.comercial.repository.OportunidadeRepository;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

	@Autowired
	private OportunidadeRepository oportunidadeRepository;
	
	@GetMapping
	public List<Oportunidade> listar() {
		return oportunidadeRepository.findAll();
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);
		
		if(oportunidade.isPresent() == false) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oportunidade.get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade adicionar(@Valid @RequestBody Oportunidade oportunidade) {
		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository
				.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());
		
		if(oportunidadeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma oportunidade para este prospecto com a mesma descrição");
		}
		
		
		return oportunidadeRepository.save(oportunidade);
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository.findById(id);
		if(oportunidadeExistente.isPresent() == false) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe esta oportunidade");
		}
		
		oportunidadeRepository.deleteById(id);
	}	
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alterar(@Valid @RequestBody Oportunidade oportunidade) {
		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository
				.findById(oportunidade.getId());
		
		if(oportunidadeExistente.isPresent() == false) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe esta oportunidade para este prospecto com a mesma descrição");
		}
		
		
		oportunidadeRepository.save(oportunidade);
	}	
	
	
}
