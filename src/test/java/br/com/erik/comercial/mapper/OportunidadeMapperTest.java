package br.com.erik.comercial.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.erik.comercial.DTO.OportunidadeDTO;
import br.com.erik.comercial.model.Oportunidade;
import br.com.erik.comercial.repository.OportunidadeRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(MockitoJUnitRunner.class)
public class OportunidadeMapperTest {
	
	@InjectMocks
	private OportunidadeMapper mapper;
	
	@Mock
	private OportunidadeRepository repository;
	
	@BeforeClass
	public static void configuracao() {
		FixtureFactoryLoader.loadTemplates("br.com.erik.comercial.fixture.dto");
		FixtureFactoryLoader.loadTemplates("br.com.erik.comercial.fixture.entity");
	}
	
	@Test
	public void deveConverteListaEntidadeParaDtoTestOK() {
		//cenario
		List<Oportunidade> listaOportunidadeEntidade = Fixture.from(Oportunidade.class).gimme(1, "valido");
		
		when(repository.findAll()).thenReturn(listaOportunidadeEntidade);
	
		//acao	
		List<OportunidadeDTO> retorno = mapper.converteListaEntidadeParaDto(repository.findAll());
	
		//verificacao		
		assertEquals(listaOportunidadeEntidade.get(0).getId(), retorno.get(0).getId());
		assertEquals(listaOportunidadeEntidade.get(0).getNomeProspecto(), retorno.get(0).getNomeProspecto());
		assertEquals(listaOportunidadeEntidade.get(0).getDescricao(), retorno.get(0).getDescricao());
		assertEquals(listaOportunidadeEntidade.get(0).getValor(), retorno.get(0).getValor());
		assertEquals(listaOportunidadeEntidade.size(), retorno.size());
		
	}
	
	@Test
	public void deveConverteEntidadeParaDtoTestOK() {
		//cenario
		Oportunidade oportunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		Optional<Oportunidade> optional = Optional.of(oportunidadeEntidade);
	
		//acao	
		OportunidadeDTO retorno = mapper.converteEntidadeParaDto(optional.get());
	
		//verificacao		
		assertEquals(oportunidadeEntidade.getId(), retorno.getId());
		assertEquals(oportunidadeEntidade.getNomeProspecto(), retorno.getNomeProspecto());
		assertEquals(oportunidadeEntidade.getDescricao(), retorno.getDescricao());
		assertEquals(oportunidadeEntidade.getValor(), retorno.getValor());
		
	}
	
	@Test
	public void deveConverteDtoParaEntidadeTestOK() {
		//cenario
		OportunidadeDTO oportunidadeDto = Fixture.from(OportunidadeDTO.class).gimme("valido");
		Optional<OportunidadeDTO> optional = Optional.of(oportunidadeDto);
	
		//acao	
		Oportunidade retorno = mapper.converteDtoParaEntidade(optional.get());
	
		//verificacao		
		assertEquals(oportunidadeDto.getId(), retorno.getId());
		assertEquals(oportunidadeDto.getNomeProspecto(), retorno.getNomeProspecto());
		assertEquals(oportunidadeDto.getDescricao(), retorno.getDescricao());
		assertEquals(oportunidadeDto.getValor(), retorno.getValor());
		
	}
}
