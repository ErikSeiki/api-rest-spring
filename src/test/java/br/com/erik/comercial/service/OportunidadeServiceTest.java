package br.com.erik.comercial.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.erik.comercial.model.Oportunidade;
import br.com.erik.comercial.repository.OportunidadeRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(MockitoJUnitRunner.class)
public class OportunidadeServiceTest {
	
	@InjectMocks
	private OportunidadeServiceImpl service;
	
	@Mock
	private OportunidadeRepository repository;
	
	@BeforeClass
	public static void configuracao() {
		FixtureFactoryLoader.loadTemplates("br.com.erik.comercial.fixture.dto");
		FixtureFactoryLoader.loadTemplates("br.com.erik.comercial.fixture.entity");
	}
	
	@Test
	public void deveListarTestOk() {
		//cenario
		List<Oportunidade> listaOporunidadeEntidade = Fixture.from(Oportunidade.class).gimme(3, "valido");
		
		when(repository.findAll()).thenReturn(listaOporunidadeEntidade);
		
		//acao
		List<Oportunidade> retorno = service.listar();
		
		//verificacao	
		assertNotNull(retorno);
		assertEquals(3, retorno.size());
		assertEquals( listaOporunidadeEntidade, retorno);
		
	}
	
	@Test
	public void deveBuscarTestOK() {
		//cenario
		Oportunidade oporunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		
		when(repository.findById(any(Long.class))).thenReturn(Optional.of(oporunidadeEntidade));
		
		//acao
		Optional<Oportunidade> retorno = service.buscar(any(Long.class));
		
		//verificacao	
		assertNotNull(retorno);
		assertEquals(Optional.of(oporunidadeEntidade), retorno);
		
	}
	
	@Test
	public void deveBuscarPeloDescricaoENomeProspectoTestOK() {
		//cenario
		Oportunidade oporunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		
		when(repository.findByDescricaoAndNomeProspecto(any(String.class), any(String.class))).thenReturn(Optional.of(oporunidadeEntidade));
		
		//acao
		Optional<Oportunidade> retorno = service.buscarPeloDescricaoENomeProspecto(oporunidadeEntidade.getDescricao(), oporunidadeEntidade.getNomeProspecto());
		
		//verificacao	
		assertNotNull(retorno);
		assertEquals(Optional.of(oporunidadeEntidade), retorno);
		
	}
	
	@Test
	public void deveAdicionarTestOK() {
		//cenario
		Oportunidade oporunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		
		when(repository.save(any(Oportunidade.class))).thenReturn(oporunidadeEntidade);
		
		//acao
		Oportunidade retorno = service.adicionar(oporunidadeEntidade);
		
		//verificacao	
		assertNotNull(retorno);
		assertEquals(oporunidadeEntidade, retorno);
		
	}
	
	@Test
	public void deveExcluirTestOK() {
		//cenario
		doNothing().when(repository).deleteById(any(Long.class));
		
		//acao
		service.excluir(any(Long.class));
		
		//verificacao	
		verify(repository, times(1)).deleteById(any(Long.class));
	}
	
	@Test
	public void deveAlterarTestOK() {
		//cenario
		Oportunidade oporunidadeEntidade = Fixture.from(Oportunidade.class).gimme("valido");
		
		when(repository.save(any(Oportunidade.class))).thenReturn(oporunidadeEntidade);
		
		//acao
		service.alterar(oporunidadeEntidade);
		
		//verificacao	
		verify(repository, times(1)).save(any(Oportunidade.class));
		
	}
}
