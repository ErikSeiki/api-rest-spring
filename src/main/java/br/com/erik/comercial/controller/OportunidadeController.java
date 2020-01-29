package br.com.erik.comercial.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.erik.comercial.DTO.OportunidadeDTO;
import br.com.erik.comercial.business.OportunidadeBusiness;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/oportunidades")
public class OportunidadeController {

	@Autowired
	private  OportunidadeBusiness oportunidadeBusiness;
	
	@GetMapping
	public ResponseEntity<List<OportunidadeDTO>> listar() {
		return ResponseEntity.ok(oportunidadeBusiness.listar());
	}
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<OportunidadeDTO> buscar(@PathVariable Long id) {
		return ResponseEntity.ok(oportunidadeBusiness.buscar(id));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OportunidadeDTO> adicionar(@Valid @RequestBody OportunidadeDTO oportunidade) {		
		return ResponseEntity.ok(oportunidadeBusiness.adicionar(oportunidade));
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<String> excluir(@PathVariable Long id) {
		return ResponseEntity.ok(oportunidadeBusiness.excluir(id));
	}	
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<String> alterar(@Valid @RequestBody OportunidadeDTO oportunidade) {
		return ResponseEntity.ok(oportunidadeBusiness.alterar(oportunidade));
	}	
	
	
}
