package com.cvc.broker.client.domain;

import java.io.Serializable;
import java.util.List;

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
public class HotelBroker implements Serializable {
	
	private static final long serialVersionUID = 4106459028389184172L;
	
	private int id;
	private String name;
	private int cityCode;
	private String cityName;
	private List<RoomBroker> rooms;
	
}
