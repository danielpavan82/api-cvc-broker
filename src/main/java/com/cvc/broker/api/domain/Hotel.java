package com.cvc.broker.api.domain;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Detalhes do hotel")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Hotel implements Serializable {
	
	private static final long serialVersionUID = 4106459028389184172L;
	
	@ApiModelProperty(notes="Id do hotel",required=true,allowEmptyValue=false,dataType="Int")
	private int id;
	
	@ApiModelProperty(notes="Nome da cidade",required=true,allowEmptyValue=false,dataType="String")
	private String cityName;
	
	@ApiModelProperty(notes="Lista dos quartos do hotel",required=true,allowEmptyValue=false,dataType="Room")
	private List<Room> rooms;

}
