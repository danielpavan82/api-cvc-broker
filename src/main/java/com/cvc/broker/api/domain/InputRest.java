package com.cvc.broker.api.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Classe para entrada da API")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class InputRest implements Serializable {
	
	private static final long serialVersionUID = -618251875655378015L;
	
	@ApiModelProperty(value="Id da cidade ou hotel", example = "0", required=true, allowEmptyValue=false, dataType="Int")
	@NotNull(message="O ID é obrigatório")
	@Min(value=1, message="O ID deve ser maior que zero")
	private int id;
	
	@ApiModelProperty(value="Data do Checkin (DD/MM/YYYY)", required=true, allowEmptyValue=false, dataType="Date")
	@NotNull(message="A Data CheckIn é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate checkIn;
	
	@ApiModelProperty(value="Data do CheckOut (DD/MM/YYYY)", required=true, allowEmptyValue=false, dataType="Date")
	@NotNull(message="A Data CheckOut é obrigatória")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate checkOut;
	
	@ApiModelProperty(value="Quantidade de Adultos", example = "0", required=true, allowEmptyValue=false, dataType="Int")
	@NotNull(message="A quantidade de adultos é obrigatória")
	@Min(value=1, message="A quantidade mínima para adulto é 1")
	private int qtdeAdultos;
	
	@ApiModelProperty(value="Quantidade de Crianças", example = "0", required=true, allowEmptyValue=false, dataType="Int")
	@NotNull(message="A quantidade de crianças é obrigatória")
	@Min(value=0, message="A quantidade para criança não pode ser negativa")
	private int qtdeCriancas;
	
}
