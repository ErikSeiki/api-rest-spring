package br.com.erik.comercial.fixture.entity;

import br.com.erik.comercial.model.Oportunidade;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class OportunidadeFixture implements TemplateLoader {

	@Override
	public void load() {
		Fixture.of(Oportunidade.class).addTemplate("valido", new Rule() {
			{
				add("id", "1");
				add("nomeProspecto", "Erik");
				add("descricao", "CLT");
				add("valor", "1100");
			}

		});
	}
}
