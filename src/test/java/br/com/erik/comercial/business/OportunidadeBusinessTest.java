package br.com.erik.comercial.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import br.com.erik.comercial.DTO.OportunidadeDTO;
import br.com.erik.comercial.mapper.OportunidadeMapper;
import br.com.erik.comercial.model.Oportunidade;
import br.com.erik.comercial.service.OportunidadeService;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(MockitoJUnitRunner.class)
public class OportunidadeBusinessTest {
	
	@InjectMocks
	private OportunidadeBusiness business;
	
	@Mock 
	private OportunidadeService service;
	
	@Mock 
	private OportunidadeMapper mapper;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@BeforeClass
	public static void configuracao() {
		FixtureFactoryLoader.loadTemplates("br.com.erik.comercial.fixture.dto");
		FixtureFactoryLoader.loadTemplates("br.com.erik.comercial.fixture.entity");
	}
	
	@Test
	public void deveListarTestOK() {
		//cenario
		List<Oportunidade> listaOperacaoEntidade = Fixture.from(Oportunidade.class).gimme(3, "valido");
		List<OportunidadeDTO> listaOperacaoDto = Fixture.from(OportunidadeDTO.class).gimme(3, "valido");
		
		when(service.listar()).thenReturn(listaOperacaoEntidade);
		when(mapper.converteListaEntidadeParaDto(listaOperacaoEntidade)).thenReturn(listaOperacaoDto);
	
		//acao	
		List<OportunidadeDTO> retorno = business.listar();
	
		//verificacao		
		assertNotNull(retorno);
		assertEquals(3, retorno.size());
		assertEquals(listaOperacaoDto, retorno);
	}
	
	@Test
	public void deveBuscarTestOK() {
		//cenario
		OportunidadeDTO oportunidadeDto = Fixture.from(OportunidadeDTO.class).gimme("valido");
		Oportunidade oportunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		
		when(service.buscar(any(Long.class))).thenReturn(Optional.of(oportunidadeEntidade));
		when(mapper.converteEntidadeParaDto(Optional.of(oportunidadeEntidade).get())).thenReturn(oportunidadeDto);
		
		//acao	
		OportunidadeDTO retorno = business.buscar(any(Long.class));
		
		//verificacao	
		assertNotNull(retorno);
		assertEquals(oportunidadeDto, retorno);
	}
	
	@Test
	public void deveBuscarTestExceptionNulo() {
		//cenario
		final String MENSAGEM = "Não existe esta oportunidade com este id"; 
		when(service.buscar(any(Long.class))).thenReturn(Optional.ofNullable(null));
		
		//verificacao	
		expectedException.expect(ResponseStatusException.class);
		expectedException.expectMessage(MENSAGEM);
		
		//acao	
		business.buscar(any(Long.class));
	}
	
	@Test
	public void deveAdicionarTestOK() {
		//cenario
		OportunidadeDTO oportunidadeDto = Fixture.from(OportunidadeDTO.class).gimme("valido");
		Oportunidade oportunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		
		when(mapper.converteDtoParaEntidade(any(OportunidadeDTO.class))).thenReturn(oportunidadeEntidade);
		when(service.buscarPeloDescricaoENomeProspecto(any(String.class), any(String.class))).thenReturn(Optional.ofNullable(null));
		when(service.adicionar(any(Oportunidade.class))).thenReturn(oportunidadeEntidade);
		when(mapper.converteEntidadeParaDto(any(Oportunidade.class))).thenReturn(oportunidadeDto);

		//acao	
		OportunidadeDTO retorno = business.adicionar(oportunidadeDto);
		
		//verificacao	
		assertNotNull(retorno);
		assertEquals(oportunidadeDto, retorno);
	}
	
	@Test
	public void deveBuscarTestExceptionOportunidadeExistente() {
		//cenario
		final String MENSAGEM ="Já existe uma oportunidade para este prospecto com a mesma descrição";
		
		OportunidadeDTO paramOportunidadeDto = Fixture.from(OportunidadeDTO.class).gimme("valido");
		Oportunidade retornoOportunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		
		when(mapper.converteDtoParaEntidade(any(OportunidadeDTO.class))).thenReturn(retornoOportunidadeEntidade);
		when(service.buscarPeloDescricaoENomeProspecto(any(String.class), any(String.class))).thenReturn(Optional.of(retornoOportunidadeEntidade));
		
		//verificacao	
		expectedException.expect(ResponseStatusException.class);
		expectedException.expectMessage(MENSAGEM);
		
		//acao	
		business.adicionar(paramOportunidadeDto);
	}
	
	@Test
	public void deveExcluirTestOK() {
		//cenario
		final String MENSAGEM = "Excluido com sucesso"; 
		
		Oportunidade retornoOportunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		
		when(service.buscar(any(Long.class))).thenReturn(Optional.of(retornoOportunidadeEntidade));

		//acao	
		String retorno = business.excluir(any(Long.class));
		
		//verificacao	
		assertNotNull(retorno);
		assertEquals(MENSAGEM, retorno);
	}
	
	@Test
	public void deveExcluirTestExceptionOportunidadeInexistente() {
		//cenario
		final String MENSAGEM = "Não existe esta oportunidade com este id"; 
		
		when(service.buscar(any(Long.class))).thenReturn(Optional.ofNullable(null));
		
		//verificacao	
		expectedException.expect(ResponseStatusException.class);
		expectedException.expectMessage(MENSAGEM);

		//acao	
		business.excluir(any(Long.class));
		
	}
	
	@Test
	public void deveAlterarTestOK() {
		//cenario
		final String MENSAGEM = "Alterado com sucesso!"; 
		
		Oportunidade retornoOportunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		OportunidadeDTO paramOportunidadeDto = Fixture.from(OportunidadeDTO.class).gimme("valido");
		
		when(mapper.converteDtoParaEntidade(any(OportunidadeDTO.class))).thenReturn(retornoOportunidadeEntidade);
		when(service.buscar(any(Long.class))).thenReturn(Optional.of(retornoOportunidadeEntidade));

		//acao	
		String retorno= business.alterar(paramOportunidadeDto);
		
		//verificacao	
		assertNotNull(retorno);
		assertEquals(MENSAGEM, retorno);
	}
	
	@Test
	public void deveAlterarTestExceptionOportunidadeInexistente() {
		//cenario
		final String MENSAGEM = "Não existe esta oportunidade com este id"; 
		
		Oportunidade retornoOportunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		OportunidadeDTO paramOportunidadeDto = Fixture.from(OportunidadeDTO.class).gimme("valido");
		
		when(mapper.converteDtoParaEntidade(any(OportunidadeDTO.class))).thenReturn(retornoOportunidadeEntidade);
		when(service.buscar(any(Long.class))).thenReturn(Optional.ofNullable(null));
		
		//verificacao	
		expectedException.expect(ResponseStatusException.class);
		expectedException.expectMessage(MENSAGEM);

		//acao	
		business.alterar(paramOportunidadeDto);
		
	}
}
