package br.com.erik.comercial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;

import br.com.erik.comercial.model.Oportunidade;

public interface OportunidadeService {
	
	public List<Oportunidade> listar();
	public Optional<Oportunidade> buscar(Long id);
	public Optional<Oportunidade> buscarPeloDescricaoENomeProspecto(String descricao, String nomeProspecto);
	public Oportunidade adicionar(Oportunidade oportunidade);
	public void excluir(@PathVariable Long id);
	public void alterar( Oportunidade oportunidade);
}
