package com.cvc.broker.client.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PriceBroker implements Serializable {
	
	private static final long serialVersionUID = 2442550329185742496L;
	
	private BigDecimal adult;
	private BigDecimal child;
	
}
