package br.com.erik.comercial.DTO;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class OportunidadeDTO {
	
	@JsonProperty("codigo")
	private Long id;
	
	@NotEmpty
	@Size(max = 80)
	@JsonProperty("nome")
	private String nomeProspecto;
	
	@NotEmpty
	@Size(max = 200)
	private String descricao;
	
	@NotNull
	@Min(0)
	private BigDecimal valor;

}
