package br.com.erik.comercial.fixture.dto;

import br.com.erik.comercial.DTO.OportunidadeDTO;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class OportunidadeDTOFixture implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(OportunidadeDTO.class).addTemplate("valido", new Rule() {
			{
				add("id", "1");
				add("nomeProspecto", "Erik");
				add("descricao", "CLT");
				add("valor", "1100");
			}

		});
	}

}
