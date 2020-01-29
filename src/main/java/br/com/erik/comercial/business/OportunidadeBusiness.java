package br.com.erik.comercial.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.erik.comercial.DTO.OportunidadeDTO;
import br.com.erik.comercial.mapper.OportunidadeMapper;
import br.com.erik.comercial.model.Oportunidade;
import br.com.erik.comercial.service.OportunidadeService;

@Component
public class OportunidadeBusiness {
	
	@Autowired
	private OportunidadeService oportunidadeService;
	
	@Autowired
	private OportunidadeMapper oportunidadeMapper;
	
	public List<OportunidadeDTO> listar() {
		List<OportunidadeDTO> listaOportunidadeDTO = oportunidadeMapper.converteListaEntidadeParaDto(oportunidadeService.listar());
		return listaOportunidadeDTO;
	}
	
	public OportunidadeDTO buscar(Long id) {
		Optional<Oportunidade> oportunidadeExistente = oportunidadeService.buscar(id);
		
		if(validacaoNulo(oportunidadeExistente)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe esta oportunidade com este id");
		}
		
		OportunidadeDTO oportunidadeDto = oportunidadeMapper.converteEntidadeParaDto(oportunidadeExistente.get());
		
		return oportunidadeDto;
	}
	
	public OportunidadeDTO adicionar(OportunidadeDTO oportunidadeDTO) {
		
		Oportunidade oportunidadeEntidade = oportunidadeMapper.converteDtoParaEntidade(oportunidadeDTO);
		
		Optional<Oportunidade> oportunidadeExistente = oportunidadeService.buscarPeloDescricaoENomeProspecto(
				oportunidadeEntidade.getDescricao(), oportunidadeEntidade.getNomeProspecto());
		
		if(oportunidadeExistente.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Já existe uma oportunidade para este prospecto com a mesma descrição");
		}
		
		return oportunidadeMapper.converteEntidadeParaDto(oportunidadeService.adicionar(oportunidadeEntidade));
	}
	
	public String excluir(Long id) {
		Optional<Oportunidade> oportunidadeExistente = oportunidadeService.buscar(id);
		
		if(validacaoNulo(oportunidadeExistente)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe esta oportunidade com este id");
		}
		
		oportunidadeService.excluir(id);
		
		return "Excluido com sucesso";
	}	
	
	public String alterar(OportunidadeDTO oportunidadeDto) {
		
		Oportunidade oportunidadeEntidade = oportunidadeMapper.converteDtoParaEntidade(oportunidadeDto);
		
		Optional<Oportunidade> oportunidadeExistente = oportunidadeService.buscar(oportunidadeEntidade.getId());
		
		if(validacaoNulo(oportunidadeExistente)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não existe esta oportunidade com este id");
		}
		
		oportunidadeService.alterar(oportunidadeEntidade);
		return "Alterado com sucesso!";
	}	
	
	
	private boolean validacaoNulo(Optional<Oportunidade> oportunidadeExistente) {
		return oportunidadeExistente.isPresent() == false;
	}
	
	
}
