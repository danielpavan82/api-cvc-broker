package com.cvc.broker.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Detalhes dos preços do quarto")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PriceDetail implements Serializable {
	
	private static final long serialVersionUID = 2442550329185742496L;
	
	@ApiModelProperty(notes="Preço por dia adulto",required=true,allowEmptyValue=false,dataType="BigDecimal")
	private BigDecimal pricePerDayAdult;
	
	@ApiModelProperty(notes="Preço por dia criança",required=true,allowEmptyValue=false,dataType="BigDecimal")
	private BigDecimal pricePerDayChild;

}
