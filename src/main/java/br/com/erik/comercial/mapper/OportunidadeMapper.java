package br.com.erik.comercial.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.erik.comercial.DTO.OportunidadeDTO;
import br.com.erik.comercial.model.Oportunidade;

@Component
public class OportunidadeMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(OportunidadeMapper.class);

	public List<OportunidadeDTO> converteListaEntidadeParaDto(List<Oportunidade> oportunidades) {
		LOGGER.info("iniciando conversao de Lista de ConexaoVwmare para Lista de ConexaoVropsDTO");
		List<OportunidadeDTO> lista = new ArrayList<>();

		for (Oportunidade oportunidadeEntidade : oportunidades) {
			OportunidadeDTO oportunidadeDto = new OportunidadeDTO();

			oportunidadeDto.setId(oportunidadeEntidade.getId());
			oportunidadeDto.setDescricao(oportunidadeEntidade.getDescricao());
			oportunidadeDto.setNomeProspecto(oportunidadeEntidade.getNomeProspecto());
			oportunidadeDto.setValor(oportunidadeEntidade.getValor());

			lista.add(oportunidadeDto);
		}

		LOGGER.info("finalizando conversao");
		return lista;

	}

	public OportunidadeDTO converteEntidadeParaDto(Oportunidade oportunidadeEntidade) {
		LOGGER.info("iniciando conversao de ConexaoVwmare para ConexaoVropsDTO");

		OportunidadeDTO oportunidadeDto = new OportunidadeDTO();

		oportunidadeDto.setId(oportunidadeEntidade.getId());
		oportunidadeDto.setDescricao(oportunidadeEntidade.getDescricao());
		oportunidadeDto.setNomeProspecto(oportunidadeEntidade.getNomeProspecto());
		oportunidadeDto.setValor(oportunidadeEntidade.getValor());


		LOGGER.info("finalizando conversao");
		return oportunidadeDto;

	}

	public Oportunidade converteDtoParaEntidade(OportunidadeDTO oportunidadeDto) {
		LOGGER.info("iniciando conversao de ConexaoVropsDTO para ConexaoVwmare");

		Oportunidade oportunidadeEntidade = new Oportunidade();

		oportunidadeEntidade.setId(oportunidadeDto.getId());
		oportunidadeEntidade.setDescricao(oportunidadeDto.getDescricao());
		oportunidadeEntidade.setNomeProspecto(oportunidadeDto.getNomeProspecto());
		oportunidadeEntidade.setValor(oportunidadeDto.getValor());

		LOGGER.info("finalizando conversao");
		return oportunidadeEntidade;
	}
}
