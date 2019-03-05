package com.cvc.broker.api.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Detalhes do quarto")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Room implements Serializable {
	
	private static final long serialVersionUID = 2442550329185742496L;
	
	@ApiModelProperty(notes="Id do quarto",required=true,allowEmptyValue=false,dataType="Int")
	private int roomID;
	
	@ApiModelProperty(notes="Categoria do quarto",required=true,allowEmptyValue=false,dataType="String")
	private String categoryName;
	
	@ApiModelProperty(notes="Preço total da viagem",required=true,allowEmptyValue=false,dataType="BigDecimal")
	private BigDecimal totalPrice;
	
	@ApiModelProperty(notes="Preços do quarto",required=true,allowEmptyValue=false,dataType="PriceDetail")
	private PriceDetail priceDetail;
	
}
