package com.cvc.broker.client.service;

import java.util.List;

import com.cvc.broker.client.domain.HotelBroker;

public interface BrokerService {
	
	List<HotelBroker> getAllHotel(int cityCode);
	HotelBroker getDetailsHotel(int hotelCode);

}
