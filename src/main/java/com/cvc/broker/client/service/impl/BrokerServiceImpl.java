package com.cvc.broker.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cvc.broker.client.domain.HotelBroker;
import com.cvc.broker.client.service.BrokerService;

@Service
public class BrokerServiceImpl implements BrokerService {

	private final static String URL_LISTA_HOTEIS_CIDADE = "https://cvcbackendhotel.herokuapp.com/hotels/avail/";
	private final static String URL_DETALHE_HOTEL = "https://cvcbackendhotel.herokuapp.com/hotels/";
	
	//Metodo para retornar a lista dos hoteis do broker de acordo com o codigo da cidade
	@Override
	public List<HotelBroker> getAllHotel(int cityCode) {
		RestTemplate restTemplate = new RestTemplate();
		HotelBroker[] lstHoteis = restTemplate.getForObject(URL_LISTA_HOTEIS_CIDADE + cityCode, HotelBroker[].class);
//		for(HotelBroker h : lstHoteis) {
//			System.out.println(h);
//		}
		return Arrays.asList(lstHoteis);
	}
	
	
	//Metodo para retornar o hotel do broker de acordo com o codigo do hotel
	@Override
	public HotelBroker getDetailsHotel(int hotelCode) {
		RestTemplate restTemplate = new RestTemplate();
		HotelBroker[] lstHoteis = restTemplate.getForObject(URL_DETALHE_HOTEL + hotelCode, HotelBroker[].class);
//		for(HotelBroker h : lstHoteis) {
//			System.out.println(h);
//		}
		return (lstHoteis.length > 0?Arrays.asList(lstHoteis).get(0):null);
	}
	
}
