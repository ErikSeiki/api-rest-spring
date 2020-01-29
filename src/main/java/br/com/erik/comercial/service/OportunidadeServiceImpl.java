package br.com.erik.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erik.comercial.model.Oportunidade;
import br.com.erik.comercial.repository.OportunidadeRepository;

@Service
public class OportunidadeServiceImpl implements OportunidadeService{
	@Autowired
	private OportunidadeRepository oportunidadeRepository;
	
	public List<Oportunidade> listar() {
		return oportunidadeRepository.findAll();
	}
	
	public Optional<Oportunidade> buscar(Long id) {
		return oportunidadeRepository.findById(id);
	}
	
	public Optional<Oportunidade> buscarPeloDescricaoENomeProspecto(String descricao, String nomeProspecto) {
		return oportunidadeRepository.findByDescricaoAndNomeProspecto(descricao, nomeProspecto);
	}
	
	
	public Oportunidade adicionar(Oportunidade oportunidade) {
		return oportunidadeRepository.save(oportunidade);
	}
	
	public void excluir(Long id) {
		oportunidadeRepository.deleteById(id);
	}	
	
	public void alterar(Oportunidade oportunidade) {		
		oportunidadeRepository.save(oportunidade);
	}	
	
	
}
