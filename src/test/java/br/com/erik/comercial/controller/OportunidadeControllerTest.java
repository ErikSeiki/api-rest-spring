package br.com.erik.comercial.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.erik.comercial.DTO.OportunidadeDTO;
import br.com.erik.comercial.business.OportunidadeBusiness;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

@RunWith(MockitoJUnitRunner.class)
public class OportunidadeControllerTest {
	
	private static final String URL = "/oportunidades";
	
	@InjectMocks
	private OportunidadeController controller;
	
	@Mock 
	private OportunidadeBusiness business;
	
	private MockMvc mock;
	
	@Before
	public void inicializar() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders
				.standaloneSetup(controller)
				.setControllerAdvice()
				.build();
	}
	
	@BeforeClass
	public static void configuracao() {
		FixtureFactoryLoader.loadTemplates("br.com.erik.comercial.fixture.dto");
		FixtureFactoryLoader.loadTemplates("br.com.erik.comercial.fixture.entity");
	}
	
	@Test
	public void deveListarTestOK() throws Exception {
		//cenario
		List<OportunidadeDTO> listaOperacaoDto = Fixture.from(OportunidadeDTO.class).gimme(3, "valido");
		
		when(business.listar()).thenReturn(listaOperacaoDto);
		String json = new ObjectMapper().writeValueAsString(listaOperacaoDto);
		

		mock
		//acao	
		.perform(get(URL))
		//verificacao	
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	@Test
	public void deveBuscarTestOK() throws Exception {
		//cenario
		OportunidadeDTO operacaoDto = Fixture.from(OportunidadeDTO.class).gimme("valido");

		when(business.buscar(any(Long.class))).thenReturn(operacaoDto);
		
		String json = new ObjectMapper().writeValueAsString(operacaoDto);
		
		
		mock
		//acao	
		.perform(get(URL + "/" + any(Long.class))
				.accept(MediaType.APPLICATION_JSON))
		//verificacao	
		.andExpect(status().isOk())
		.andExpect(content().string(json));
	}
	
	@Test
	public void deveAdicionarTestOK() throws Exception {
		//cenario
		OportunidadeDTO paramOperacaoDto = Fixture.from(OportunidadeDTO.class).gimme("valido");
		OportunidadeDTO returnOperacaoDto = Fixture.from(OportunidadeDTO.class).gimme("valido");
		
		String paramJson = new ObjectMapper().writeValueAsString(paramOperacaoDto);

		when(business.adicionar(any(OportunidadeDTO.class))).thenReturn(returnOperacaoDto);
		
		String retornoJson = new ObjectMapper().writeValueAsString(returnOperacaoDto);
		
		
		mock
		//acao
			.perform(post(URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(paramJson)
					.accept(MediaType.APPLICATION_JSON))
			//verificacao	
			.andExpect(status().isOk())
			.andExpect(content().string(retornoJson));
	}
	
	@Test
	public void deveRemoverTestOK() throws Exception {
		//cenario
		final String MENSAGEM = "Excluido com sucesso"; 
		
		when(business.excluir(any(Long.class))).thenReturn(MENSAGEM);
		
		mock
		//acao
			.perform(delete(URL+"/"+any(Long.class))
					.accept(MediaType.APPLICATION_JSON))
			//verificacao	
			.andExpect(status().isOk())
			.andExpect(content().string(MENSAGEM));
	}
	
	@Test
	public void deveAlterarTestOK() throws Exception {
		//cenario
		final String MENSAGEM = "Alterado com sucesso!"; 
		OportunidadeDTO operacaoDto = Fixture.from(OportunidadeDTO.class).gimme("valido");
		
		String json = new ObjectMapper().writeValueAsString(operacaoDto);

		when(business.alterar(any(OportunidadeDTO.class))).thenReturn(MENSAGEM);
		
		mock
		//acao
			.perform(put(URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(json)
					.accept(MediaType.APPLICATION_JSON))
			//verificacao	
			.andExpect(status().isOk())
			.andExpect(content().string(MENSAGEM));
	}
	
	
}
